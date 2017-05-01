package com.gitfresh.gfresh;

import org.springframework.data.annotation.Id;


public class GFreshUser {
	@Id
	private int id;
	private String userName;
	private int followersCount;
	private String location;
	
	public GFreshUser() {
		
	}
	public GFreshUser(int id, String name, int count, String location) {
		this.id = id;
		this.userName = name;
		this.followersCount = count;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "User ID: " + id + " Name: " + userName + " Followers: " + followersCount + " Location: " + location;
	}
}
