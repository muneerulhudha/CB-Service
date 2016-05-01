package com.cb.model;

public class Profile {

	String username;
	String name;
	String password;
	String email;
	String dept;
	String address;
	String phoneno;
	String lastLoginTime;
	
	public Profile(String username, String name, String password, String email,
			String dept, String address, String phoneno, String lastLoginTime) {

		this.username = username;
		this.name = name;
		this.email = email;
		this.dept = dept;
		this.address = address;
		this.phoneno = phoneno;
		this.password = password;
		this.lastLoginTime = lastLoginTime;
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getDept() {
		return dept;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
