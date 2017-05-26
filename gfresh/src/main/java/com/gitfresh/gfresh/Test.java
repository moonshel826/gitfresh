package com.gitfresh.gfresh;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// Store search result returned by github-api
		List<String> lines = UserService.readFile("cities.txt");	
		for (int j = 0; j <= lines.size(); j++) {
			UserService.getUserService().searchByQuery("location:" + lines.get(j));
			for(int i = 1; i <= 100; i++) {
				UserService.getUserService().searchByQuery("location:" + lines.get(j) + " followers:<" + i);
			}
		}
		UserService.getUserService().cleanUsers();
		
		// Update search result by using java.net and java.io
		UserService.getUserService().updateUsers(0);
		


	}

}
