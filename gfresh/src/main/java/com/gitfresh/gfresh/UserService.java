package com.gitfresh.gfresh;

import org.json.JSONObject;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.BufferedReader;
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

	private static UserDAO userDao;
	private static UserService userService;

	private UserService() {

	}

	public static UserService getUserService() {
		if (userService == null) {
			userDao = UserDAO.getUserDAO();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) {
		InputStream is = null;
		JSONObject json = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
			return json;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return json;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return json;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<GFreshUser> searchByQuery(String query) {
		List<GFreshUser> res = new ArrayList<GFreshUser>();
		try {
			
			GitHub github = GitHub.connect("moonshel826", userDao.getTokenInfo().getToken());
			GHUserSearchBuilder search = github.searchUsers().q(query);

			for (GHUser item : search.list()) {

				GFreshUser user = userDao.readById(item.getId());
				if (user == null) {
					user = new GFreshUser(item.getId(), item.getLogin(), item.getName(), item.getFollowersCount(),
							item.getLocation(), item.getEmail(), item.getUrl(), item.getHtmlUrl(), item.getBlog(),
							item.getCompany(), item.getRepositories(), item.getPublicRepoCount());

					userDao.create(user);
					System.out.println("--Creat user: " + user);
				}
				res.add(user);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public void updateUsers() {
		for (GFreshUser user : userDao.findAllUsers()) {
			if ("".equals(user.getBio())  && "".equals(user.getHireable()) && !user.getLocation().startsWith("Tempe")) {

				JSONObject json = readJsonFromUrl(user.getUrl() + "?access_token=" + userDao.getTokenInfo().getToken());
				user.setBio(json.get("bio") instanceof String ? (String) json.get("bio") : "");
				user.setHireable(
						json.get("hireable") instanceof Boolean ? ((Boolean) json.get("hireable")).toString() : "");
				userDao.updateUser(user);
				System.out.println("--Update user: -Bio: " + user.getBio() + " -Hireable: " + user.getHireable());
				System.out.println(userDao.readById(user.getId()));
			}
			
		}

	}
}
