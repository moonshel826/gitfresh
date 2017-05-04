package com.gitfresh.gfresh;

public class Test {


	public static void main(String[] args) {
		 for (GFreshUser user :UserService.getUserService().searchByQuery("location:Tempe")) {
			 System.out.println(user);
		 }
		 UserService.getUserService().updateUsers();
	}

}
