package com.store.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.bookstore.entity.Order;

@Repository
public interface IOrderDao extends JpaRepository<Order, Integer>{
	
	@Query(value = "DELETE FROM order WHERE user_id = :userId", nativeQuery = true)
	public void deleteOrderByUserId(Integer userId);

	@Query(value = "SELECT o FROM Order o JOIN FETCH o.userId u WHERE u.userId = :userId")
	public List<Order> findAllByUserId(Integer userId);

}
