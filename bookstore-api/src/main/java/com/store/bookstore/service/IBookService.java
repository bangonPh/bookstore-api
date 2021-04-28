package com.store.bookstore.service;

import java.util.List;

import com.store.bookstore.entity.Book;

public interface IBookService {
	
	public List<Book> findAllByOrderByIsRecommendedDesc();
	
	public Book findByBookId(Integer bookId);

}
