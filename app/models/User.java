package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import helpers.PasswordHelper;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="users")
public class User extends Model{
	@Id
	@GeneratedValue
	private Long id;
	@Required
	private String firstName;
	@Required
	private String lastName;
	@Required
	@Email
	private String email;
	@Password
	private String password;
	private Timestamp onCreated;
	// default value for the isAdmin is false
	private Boolean isAdmin = false;
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Timestamp getOnCreated() {
		return onCreated;
	}

	public void setOnCreated(Timestamp onCreated) {
		this.onCreated = onCreated;
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
	
	public static User findById(Long id) {
		return User.find("byId", id).first();
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName +" (" + email +")";
	}
}
