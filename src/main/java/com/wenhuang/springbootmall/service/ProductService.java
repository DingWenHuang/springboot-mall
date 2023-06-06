package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.ProductRequest;
import com.wenhuang.springbootmall.model.Product;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
