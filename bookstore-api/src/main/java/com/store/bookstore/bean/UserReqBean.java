package com.store.bookstore.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserReqBean {

	private String username;
	private String password;
	@JsonProperty("date_of_birth")
	private String dateOfbirthday;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfbirthday() {
		return dateOfbirthday;
	}
	public void setDateOfbirthday(String dateOfbirthday) {
		this.dateOfbirthday = dateOfbirthday;
	}
	
}
