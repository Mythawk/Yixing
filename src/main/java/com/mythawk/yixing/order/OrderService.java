package com.mythawk.yixing.order;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Resource
    private IOrderDao orderDao;

    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    @Override
    public List<OrderData> selectOrderByStatus(String status,String numb) {
        return orderDao.selectOrderByStatus(status,numb);
    }

    @Override
    public List<Order> selectOrderById(String id) {
        return orderDao.selectOrderById(id);
    }

    @Override
    public int editOrderStatus(Order order) {
        return orderDao.editOrderStatus(order);
    }

    @Override
    public int deleteOrderById(Order order) {
        return orderDao.deleteOrderById(order);
    }

    @Override
    public List<OrderData> selectAll(String numb) {
        return orderDao.selectAll(numb);
    }

    @Override
    public List<Order> findByStatus(String status) {
        return orderDao.findByStatus(status);
    }
}
