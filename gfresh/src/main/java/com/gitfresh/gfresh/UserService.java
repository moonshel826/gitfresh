package com.gitfresh.gfresh;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.springframework.data.mongodb.core.MongoTemplate;


import com.mongodb.MongoClient;

public class UserService {
	private static final String DB_NAME = "gfresh";
	private static final String MONGO_HOST = "ZMa-MBP.local";
	private static final int MONGO_PORT = 27017;
	private static UserDAO userDao;
	private static UserService userService;
	
	private UserService() {
		
	}
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
			try {
				MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
				MongoTemplate mongoOps = new MongoTemplate(mongo, DB_NAME);
				userDao = new UserDAO(mongoOps);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
