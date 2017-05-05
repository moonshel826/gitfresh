package com.gitfresh.gfresh;

public class Test {

	public static void main(String[] args) {
		// Store search result returned by github-api
		for (GFreshUser user : UserService.getUserService().searchByQuery("location:AZ")) {
			System.out.println(user);
		}
		// Update search result by using java.net and java.io
		UserService.getUserService().updateUsers();

	}

}
