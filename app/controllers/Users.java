package controllers;

import models.User;
import play.data.validation.Equals;
import play.data.validation.Required;

public class Users extends CRUD{
	/*
	 * renders the signup page
	 */
	public static void signup() throws Exception{
		render();
	}
	
	/*
	 * creates new user
	 */
	public static void create(
			@Required String firstName,
			@Required String lastName,
			@Required String email,
			@Required String password,
			@Equals(value="password", message ="Passwords do not match")  
			String passwordConfirmation) throws Exception{
		// Check if the email already exists
		validation.isTrue("emailExistence", 
				User.find("byEmail", email).first() == null)
				.message("Email already exists");
		
		if(validation.hasErrors()) {
		    params.flash();  
		    validation.keep();
		    // redirect to sign-up page back
		    signup();
		} else {
			new User(firstName, lastName, email, password).save();
			// redirect to home page
			Application.index();
		}
	}
	
	public static void login() {
		render();
	}
	
	public static void authenticate(String email, String password) {
		boolean authentic = Security.authenticate(email, password);
		validation.isTrue("loggedIn", authentic)
			.message("Email and Passwords do not match");
	    if (authentic) {
	    	Application.index();
	    } else {
	    	// has errors
		    params.flash();  
	    	validation.keep();
	    	Users.login();
	    }
	}
}
