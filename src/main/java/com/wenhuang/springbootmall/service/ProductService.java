package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.ProductRequest;
import com.wenhuang.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);
}
