package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
