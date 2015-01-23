package models;

import java.util.Date;

import play.db.jpa.Model;

public class SchoolStatus extends Model{
	private Profile profile;
	private School school;
	private Date expectedGraduationDate;
	private DegreeType degreeType;
	private String major;
	private String secondaryMajor;
	private String minor;
	
	public enum DegreeType {
		Secondary, Bachelor, Master, Doctoral;
	}
}
