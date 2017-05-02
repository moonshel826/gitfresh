package com.gitfresh.gfresh;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.List;

public class UserDAO {
	private static final String DB_NAME = "gfresh";
	private static final String MONGO_HOST = "ZMa-MBP.local";
	private static final int MONGO_PORT = 27017;
	private static MongoTemplate mongoOps;
	private static final String USER_COLLECTION = "user";
	private static UserDAO userDao;
	
	private UserDAO() {

	}

	public static UserDAO getUserDAO() {
		if (userDao == null) {
			try {
				mongoOps = new MongoTemplate(new MongoClient(MONGO_HOST, MONGO_PORT), DB_NAME);
				userDao = new UserDAO();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userDao;
	}
	public void create(GFreshUser user) {
		this.mongoOps.insert(user, USER_COLLECTION);
	}
	
	public GFreshUser readById(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, GFreshUser.class, USER_COLLECTION);
	}
	
	public List<GFreshUser> getByLocation(String location) {
		Query query = new Query(Criteria.where("location").is(location));
		return this.mongoOps.find(query, GFreshUser.class, USER_COLLECTION);
	}
	
	public List<GFreshUser> getByFolowwers(int min, int max) {
		Query query = new Query(Criteria.where("followersCount").gte(min).lte(min));
		return this.mongoOps.find(query, GFreshUser.class, USER_COLLECTION);
	}
	
}
