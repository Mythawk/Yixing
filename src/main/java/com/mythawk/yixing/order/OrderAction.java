package com.mythawk.yixing.order;

import com.alibaba.fastjson.JSON;
import com.mythawk.yixing.share.ShareBody;
import com.mythawk.yixing.sneaker.Sneaker;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderAction {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/main")
    public ModelAndView sneakerMain(){
        System.out.println("**********");
        ModelAndView mv = new ModelAndView();
        mv.addObject("order",orderService.findByStatus("待发货"));
        mv.setViewName("order/order-main");
        return mv ;
    }
    @RequestMapping("/main2")
    public ModelAndView sneakerMain2(){
        System.out.println("**********");
        ModelAndView mv = new ModelAndView();
        mv.addObject("order",orderService.findByStatus("退货中"));
        mv.setViewName("order/order-main2");
        return mv ;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable String  id) {
        System.out.println(id);
        Order order =new Order();
        order.setOrder_id(id);
        order.setOrder_status("待收货");
        orderService.editOrderStatus(order);
        return "redirect:/order/main";
    }


    @RequestMapping(value = "/edit2/{id}",method = RequestMethod.GET)
    public String edit2(@PathVariable String  id) {
        System.out.println(id);
        Order order =new Order();
        order.setOrder_id(id);
        order.setOrder_status("完成退货");
        orderService.editOrderStatus(order);
        return "redirect:/order/main2";
    }

    @ResponseBody
    @RequestMapping(value = "/addOrder" , method = RequestMethod.POST)
    public Map requestAddOrder(@RequestBody Map<String,String> orderData){
        System.out.println(orderData);
        String orders = orderData.get("data");
        List<Order> orderList = JSON.parseArray(orders,Order.class);
        int total = 0;
        for (Order order :orderList){
            total +=  orderService.insertOrder(order);
        }
        Map map =new HashMap();
        if (total==orderList.size()){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/selectOrder" , method = RequestMethod.POST)
    public Map requestSelectOrder(@RequestBody Map<String,String> orderData){
        System.out.println(orderData);
        String status = orderData.get("status");
        String numb = orderData.get("numb");
        System.out.println(status);
        List<OrderData> orderDataList = new ArrayList<>();
        if (status.equals("ALL")){
            orderDataList = orderService.selectAll(numb);
        }else {
            orderDataList = orderService.selectOrderByStatus(status,numb);
        }
        Map map =new HashMap();
        double order_sum = 0.0;
        if (orderDataList != null ){
            List<OrderList> orderLists = new ArrayList<>();
            for (OrderData orderData1:orderDataList){
                List<Order> orderList = orderService.selectOrderById(orderData1.getOrder_id());
                for (Order order:orderList){
                    order_sum += order.getSneaker_price()* order.getSneaker_number();
                }
                orderData1.setOrder_sum(order_sum);
                order_sum = 0.0;
                OrderList orderList1 =new OrderList(orderData1,orderList);
                orderLists.add(orderList1);
            }
            map.put("status","ok");
            map.put("data",orderLists);
        }else {
            map.put("status","false");
            map.put("data",null);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/editOrder" , method = RequestMethod.POST)
    public Map requestEditOrder(@RequestBody Map<String,String> orderData){
        System.out.println(orderData);
        int action = Integer.parseInt(orderData.get("action"));
        String orderId = orderData.get("orderId");
        int result = 0;
        switch (action){
            case 1:
                Order order = new Order();
                order.setOrder_status("待发货");
                order.setOrder_id(orderId);
                result = orderService.editOrderStatus(order);
                break;
            case 2:
                Order order2 =new Order();
                order2.setOrder_id(orderId);
                result = orderService.deleteOrderById(order2);
                break;
            case 3:
                Order order3 = new Order();
                order3.setOrder_status("已完成");
                order3.setOrder_id(orderId);
                result = orderService.editOrderStatus(order3);
                break;
            case 4:
                Order order4 = new Order();
                order4.setOrder_status("退货中");
                order4.setOrder_id(orderId);
                result = orderService.editOrderStatus(order4);
                break;

        }

        Map map =new HashMap();

        if (result!= 0){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }


}
