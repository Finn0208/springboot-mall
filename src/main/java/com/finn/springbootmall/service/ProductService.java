package com.finn.springbootmall.service;

import com.finn.springbootmall.constant.ProductCategory;
import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
