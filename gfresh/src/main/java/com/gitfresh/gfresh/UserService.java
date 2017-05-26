package com.gitfresh.gfresh;

import org.json.JSONObject;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class UserService {

	// Singleton
	private static UserDAO userDao  = UserDAO.getUserDAO();
	private static UserService userService;
	private static final String token = userDao.getTokenInfo().getToken();

	private UserService() {

	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	private static String readAll(Reader rd) {
		StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) {
		InputStream is = null;
		JSONObject json = null;
		try {
			// java.net and java.io
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
			return json;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return json;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<String> readFile(String filename){
		BufferedReader abc;
		List<String> lines = new ArrayList<String>();
		try {
			abc = new BufferedReader(new FileReader(filename));		
			String line = null;
			while((line = abc.readLine()) != null) {
//				if(line.endsWith("County seat")) {
//					lines.add("\"" + line.substring(0, line.length() - 11) + "\"");
//				} else {
//					lines.add("\"" + line + "\"");
//				}  
				lines.add(line);
			}
			System.out.println("Reading file in User Service: " + lines);
			abc.close();
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public List<GFreshUser> searchByQuery(String query) {
		List<GFreshUser> res = new ArrayList<GFreshUser>();
		try {
			// github-api
			GitHub github = GitHub.connect("moonshel826", token);
			GHUserSearchBuilder search = github.searchUsers().q(query);
			List<String> lines = readFile("states.txt");
			
			for (GHUser item : search.list()) {
				boolean validatLocation = true;
				for (String state : lines) {
					if (item.getLocation().endsWith(" "+ state)) {
						validatLocation = false;
						break;
					}
				}
				if (validatLocation) {
					GFreshUser user = userDao.readById(item.getId());
					if (user == null) {
						user = new GFreshUser(item.getId(), item.getLogin(), item.getName(), item.getFollowersCount(),
								item.getLocation(), item.getEmail(), item.getUrl(), item.getHtmlUrl(), item.getBlog(),
								item.getCompany(), item.getRepositories(), item.getPublicRepoCount());
	
						userDao.createUser(user);
						System.out.println("--Creat user: " + user);
					}
					res.add(user);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void updateUsers(int beginRow) {
		List<GFreshUser> users = userDao.findAllUsers();
		for (int i = beginRow; i < users.size(); i++) {
			GFreshUser user = users.get(i);
			if ("".equals(user.getBio()) && "".equals(user.getHireable())) {
				JSONObject json = readJsonFromUrl(user.getUrl() + "?access_token=" + token);
				user.setBio(json.get("bio") instanceof String ? (String) json.get("bio") : "");
				user.setHireable(
						json.get("hireable") instanceof Boolean ? ((Boolean) json.get("hireable")).toString() : "");
				userDao.updateUser(user);
				System.out.println("--Update user: -Bio: " + user.getBio() + " -Hireable: " + user.getHireable());
				System.out.println(userDao.readById(user.getId()));
			}
		}
	}
	
	public void cleanUsers() {
		List<String> lines = UserService.readFile("states.txt");
		int count = 0;
		for (GFreshUser user : userDao.findAllUsers()) {
			for (String state : lines) {
				if (user.getLocation().endsWith(state) && !user.getLocation().contains("CA") && !user.getLocation().contains("San Francisco") 
						&& !user.getLocation().contains("SAN FRANCISCO") && !user.getLocation().contains("California")) {
					System.out.println(count + " " + user.getLocation());
					userDao.deleteUser(user);
					break;
				}		
			}
			count++;
		}	
	}
}
