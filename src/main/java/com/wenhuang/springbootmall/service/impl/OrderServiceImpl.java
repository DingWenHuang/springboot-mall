package com.wenhuang.springbootmall.service.impl;

import com.wenhuang.springbootmall.dao.OrderDao;
import com.wenhuang.springbootmall.dao.ProductDao;
import com.wenhuang.springbootmall.dto.BuyItem;
import com.wenhuang.springbootmall.dto.CreateOrderRequest;
import com.wenhuang.springbootmall.model.OrderItem;
import com.wenhuang.springbootmall.model.Product;
import com.wenhuang.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //calculate total amount
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            //transfer BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        //create order
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
