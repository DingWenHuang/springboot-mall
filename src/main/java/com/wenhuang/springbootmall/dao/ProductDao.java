package com.wenhuang.springbootmall.dao;

import com.wenhuang.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
