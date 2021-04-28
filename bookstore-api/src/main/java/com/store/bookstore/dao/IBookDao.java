package com.store.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.bookstore.entity.Book;

@Repository
public interface IBookDao extends JpaRepository<Book, Integer>{
	
	public List<Book> findAllByOrderByIsRecommendedDesc();
	
	public Book findByBookId(Integer bookId);

}
