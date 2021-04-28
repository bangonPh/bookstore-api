package com.store.bookstore.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	
	private Integer userId;
	private String username;
	private String password;
	private String name;
	private String surname;
	
	@JsonProperty("date_of_birthday")
	private Date dateOfbirthday;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDateOfbirthday() {
		return dateOfbirthday;
	}
	public void setDateOfbirthday(Date dateOfbirthday) {
		this.dateOfbirthday = dateOfbirthday;
	}
	
}
