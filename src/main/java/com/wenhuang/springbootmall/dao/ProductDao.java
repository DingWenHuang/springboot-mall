package com.wenhuang.springbootmall.dao;

import com.wenhuang.springbootmall.constant.ProductCategory;
import com.wenhuang.springbootmall.dto.ProductQueryParams;
import com.wenhuang.springbootmall.dto.ProductRequest;
import com.wenhuang.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer creatProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId, Integer stock);

    void deleteProductById(Integer productId);
}
