package org.sofyan.ldapdemo;

public class Person {
	
	private String firstName;
	private String lastName;
	private String fullName;
	private String givenName;
	private String description;
	private String uuid;
	private String userpassword;
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGivenName() {
		return this.firstName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
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
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

}
