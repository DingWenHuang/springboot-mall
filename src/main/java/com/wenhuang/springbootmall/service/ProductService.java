package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.constant.ProductCategory;
import com.wenhuang.springbootmall.dto.ProductRequest;
import com.wenhuang.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
