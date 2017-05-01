package com.gitfresh.gfresh;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserDAO {
	private MongoTemplate mongoOps;
	private static final String USER_COLLECTION = "user";
	
	public UserDAO(MongoTemplate mongoOps) {
		this.mongoOps = mongoOps;
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
