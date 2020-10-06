package com.mythawk.yixing.order;

import java.util.List;

public class OrderList {

    private OrderData orderData;
    private List<Order> orderList;

    public OrderList(OrderData orderData, List<Order> orderList) {
        this.orderData = orderData;
        this.orderList = orderList;
    }

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
