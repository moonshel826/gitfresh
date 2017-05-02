package com.gitfresh.thrift.gfresh;

import com.gitfresh.gfresh.UserService;

public class Test {

	public static void main(String[] args) {
		for (com.gitfresh.gfresh.GFreshUser user :UserService.getUserService().searchByQuery("moonshel")) {
			System.out.println(user);
		}

	}

}
