package models;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;

import play.db.jpa.Model;

public class Profile extends Model {
	private User user;
	private DateTime onCreated;
	private DateTime onUpdated;
	// personal information
	private Date dateOfBirth;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String phone;
	private String emailCollege;
	private String emailPersonal;
	private Picture profilePhoto;
	public enum ApplicationStatus {
		None, Pending, Holding, Accepted, Rejected; 
	}
	private ApplicationStatus applicationStatus;
	//school information
	private Collection<SchoolStatus> schoolStatus;
	//others
	private String reasonForApplication;
	
}
