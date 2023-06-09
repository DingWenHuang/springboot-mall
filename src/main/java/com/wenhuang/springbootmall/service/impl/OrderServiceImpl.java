package com.wenhuang.springbootmall.service.impl;

import com.wenhuang.springbootmall.dao.OrderDao;
import com.wenhuang.springbootmall.dao.ProductDao;
import com.wenhuang.springbootmall.dao.UserDao;
import com.wenhuang.springbootmall.dto.BuyItem;
import com.wenhuang.springbootmall.dto.CreateOrderRequest;
import com.wenhuang.springbootmall.model.Order;
import com.wenhuang.springbootmall.model.OrderItem;
import com.wenhuang.springbootmall.model.Product;
import com.wenhuang.springbootmall.model.User;
import com.wenhuang.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // check if userId exist or not
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("this user ID : {} is not exist!", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // check if product is exist or not and product stock is sufficient or not
            if (product == null) {
                log.warn("this product : {} is not exist!", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("product : {} stock is not sufficient! Stock : {}, Quantity to buy : {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

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
