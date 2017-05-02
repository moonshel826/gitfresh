package com.gitfresh.gfresh;

public class Test {

	public static void main(String[] args) {
		for (com.gitfresh.gfresh.GFreshUser user :UserService.getUserService().searchByQuery("moonshel")) {
			System.out.println(user);
		}

	}

}
