package com.gitfresh.gfresh;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
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
	
	public List<GFreshUser> searchByQuery(String query) {
		List<GFreshUser> res = new ArrayList<GFreshUser>();
		try {
			GitHub github;
			github = GitHub.connectAnonymously();
			GHUserSearchBuilder search = github.searchUsers();
	        search.q(query);
	       
	        for (GHUser item : search.list()) {
	        	GFreshUser user = userDao.readById(item.getId());
	        	if (user == null) {
	        		user = new GFreshUser(item.getId(), item.getLogin(), item.getFollowersCount(), item.getLocation());
	        		
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

	
}
