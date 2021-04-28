package com.store.bookstore.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResBean {
	
	private String name;
	private String surname;
	@JsonProperty("date_of_birth")
	private String dateOfbirthday;
	private List<Integer> books;
	
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
	public String getDateOfbirthday() {
		return dateOfbirthday;
	}
	public void setDateOfbirthday(String dateOfbirthday) {
		this.dateOfbirthday = dateOfbirthday;
	}
	public List<Integer> getBooks() {
		return books;
	}
	public void setBooks(List<Integer> books) {
		this.books = books;
	}

}
