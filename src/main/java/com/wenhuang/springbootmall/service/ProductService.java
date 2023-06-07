package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.ProductRequest;
import com.wenhuang.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
