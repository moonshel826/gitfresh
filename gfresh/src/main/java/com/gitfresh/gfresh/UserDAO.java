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
	private static final String Token_COLLECTION = "token";
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
		mongoOps.insert(user, USER_COLLECTION);
	}
	
	public GFreshUser readById(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoOps.findOne(query, GFreshUser.class, USER_COLLECTION);
	}
	
	public List<GFreshUser> findAllUsers() {
		return mongoOps.findAll(GFreshUser.class, USER_COLLECTION);
	}
	
	public void updateUser(GFreshUser user) {	
		mongoOps.save(user, USER_COLLECTION);
	}
	
	public Token getTokenInfo() {
		Query query = new Query(Criteria.where("_id").is(1));
		return mongoOps.findOne(query, Token.class, Token_COLLECTION);
	}
	
//	public List<GFreshUser> getByLocation(String location) {
//		Query query = new Query(Criteria.where("location").is(location));
//		return mongoOps.find(query, GFreshUser.class, USER_COLLECTION);
//	}
//	
//	public List<GFreshUser> getByFolowwers(int min, int max) {
//		Query query = new Query(Criteria.where("followersCount").gte(min).lte(min));
//		return mongoOps.find(query, GFreshUser.class, USER_COLLECTION);
//	}
	
}
