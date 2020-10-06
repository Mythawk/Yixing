package com.mythawk.yixing.order;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IOrderDao {

    int insertOrder(Order order);
    List<OrderData> selectOrderByStatus(@Param("status") String status,@Param("numb")String numb);
    List<Order> selectOrderById(@Param("order_id")String id);
    int editOrderStatus(Order order);
    int deleteOrderById(Order order);
    List<OrderData> selectAll(@Param("numb")String numb);
    List<Order> findByStatus(@Param("status") String status);
}
