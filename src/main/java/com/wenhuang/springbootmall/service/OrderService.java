package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.CreateOrderRequest;
import com.wenhuang.springbootmall.dto.OrderQueryParams;
import com.wenhuang.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
