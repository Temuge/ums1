package models;

import java.util.Date;

public class SchoolStatus {
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
