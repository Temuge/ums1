package models;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import helpers.PasswordHelper;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model{
	@Required
	private String firstName;
	@Required
	private String lastName;
	@Required
	@Email
	private String email;
	@Password
	private String password;
	private DateTime onCreated;
	private DateTime onUpdated;
	// default value for the isAdmin is false
	private boolean isAdmin = false;
	
	public User(
			String firstName, 
			String lastName, 
			String email, 
			String password) {
		try {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = PasswordHelper.getSaltedHash(password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public static User connect(String email, String password) {
        User user = find("byEmail", email).first();
        try {
        	return PasswordHelper.check(password, user.password) ? user : null;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	@Override
	public String toString() {
		return firstName + " " + lastName +" (" + email +")";
	}
}
