package com.store.bookstore.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.bookstore.bean.BookBean;
import com.store.bookstore.dto.UserDto;
import com.store.bookstore.entity.Book;
import com.store.bookstore.entity.User;
import com.store.bookstore.exception.UserNotAvailableException;
import com.store.bookstore.service.IBookService;
import com.store.bookstore.service.IUserService;
import com.store.bookstore.util.Constant;

@RestController
public class BookController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	public IBookService bookService;
	
	@Autowired
	public IUserService userService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<BookBean> getBooks(HttpSession httpSession) throws UserNotAvailableException {
		// Check Session login 
		UserDto sessionLogin = getUserLogin(httpSession);
		if (null == sessionLogin) {
			throw new UserNotAvailableException("Does not exits user login");
		}
		
		List<BookBean> bookBeanList = new ArrayList<BookBean>();
		
		// Find All Book by IsRecommendedDesc
		List<Book> bookList = bookService.findAllByOrderByIsRecommendedDesc();
		for(Book book : bookList) {
			BookBean bookBean = new BookBean();
			bookBean.setBookId(book.getBookId());
			bookBean.setBookName(book.getBookName());
			bookBean.setAuthor(book.getAuthor());
			bookBean.setPrice(book.getPrice());
			bookBean.setIsRecommended(Constant.ONE == book.getIsRecommended()? true : false);
			bookBeanList.add(bookBean);
		}
		return bookBeanList;
	}

}
