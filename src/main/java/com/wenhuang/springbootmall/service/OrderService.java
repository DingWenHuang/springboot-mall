package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.CreateOrderRequest;
import com.wenhuang.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
