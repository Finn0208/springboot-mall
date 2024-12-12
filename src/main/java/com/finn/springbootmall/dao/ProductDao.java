package com.finn.springbootmall.dao;

import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
