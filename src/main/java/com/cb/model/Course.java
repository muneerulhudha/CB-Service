package com.cb.model;

public class Course {
	
	String courseName;
	String courseNumber;
	int credits;
	String term;
	String classNumber;
	String classSection;
	
	public Course(int credits,String courseName, String courseNumber, String term, String classNumber,String classSection) {
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.term = term;
		this.credits = credits;
		this.classNumber = classNumber;
		this.classSection = classSection;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public String getClassSection() {
		return classSection;
	}

	public void setClassSection(String classSection) {
		this.classSection = classSection;
	}
		
}