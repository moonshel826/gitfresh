package com.gitfresh.gfresh;


public class App 
{
	
    public static void main(String[] args)
    {     
        for(GFreshUser user: UserService.getUserService().searchByQuery("moonshel")) {
        	System.out.println(user);
        }
        
    }
}
