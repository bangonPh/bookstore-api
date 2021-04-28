package com.store.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.bookstore.entity.OrderDetail;

@Repository
public interface IOrderDetailDao extends JpaRepository<OrderDetail, Integer>{
	
	@Query(value = " SELECT OD.ORDER_DETAIL_ID , OD.ORDER_ID , OD.BOOK_ID , OD.PRICE  "
			+" FROM `ORDER` O   "
			+" JOIN ORDER_DETAIL OD ON OD.ORDER_ID = O.ORDER_ID "
			+" WHERE O.USER_ID = :userId "
			+" ORDER BY 1 ",nativeQuery = true)
	public List<Object[]> getOrderDetailsByUserId(@Param("userId")  Integer userId);
	
	@Query(value = "DELETE FROM order_detail WHERE order_id = :orderId", nativeQuery = true)
	public void deleteOrderDetailByOrderId(Integer orderId);

}
