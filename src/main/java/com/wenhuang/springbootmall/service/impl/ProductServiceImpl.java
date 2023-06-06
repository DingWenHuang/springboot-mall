package com.wenhuang.springbootmall.service.impl;

import com.wenhuang.springbootmall.dao.ProductDao;
import com.wenhuang.springbootmall.model.Product;
import com.wenhuang.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
