package com.store.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.bookstore.dao.IBookDao;
import com.store.bookstore.entity.Book;

@Service
public class BookService implements IBookService{
	
	@Autowired
	public IBookDao bookDao;

	@Override
	public List<Book> findAllByOrderByIsRecommendedDesc() {
		return bookDao.findAllByOrderByIsRecommendedDesc();
	}

	@Override
	public Book findByBookId(Integer bookId) {
		return bookDao.findByBookId(bookId);
	}

	

}
