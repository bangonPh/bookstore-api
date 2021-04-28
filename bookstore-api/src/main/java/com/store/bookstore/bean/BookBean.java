package com.store.bookstore.bean;

public class BookBean {
	
    private Integer bookId;
    private String bookName;
    private double price;
    private String author;
    private boolean isRecommended;
    
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

}
