package com.mythawk.yixing.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {

    int insertOrder(Order order);
    List<OrderData> selectOrderByStatus(String status,String numb);
    List<Order> selectOrderById(String id);
    int editOrderStatus(Order order);
    int deleteOrderById(Order order);
    List<OrderData> selectAll(String numb);
    List<Order> findByStatus(String status);
}
