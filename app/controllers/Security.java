package controllers;

import models.User;
public class Security extends Secure.Security {
	
	public static boolean authenticate(String email, String password) {
	    return User.connect(email, password) != null;
	}
	
	static void onDisconnected(){
	    try {
	    	Users.login();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	static void onAuthenticated() {
	    Application.index();
	}
	
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return User.find("byEmail", connected()).<User>first().isAdmin();
	    }
	    return false;
	}
}
