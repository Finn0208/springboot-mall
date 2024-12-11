package com.finn.springbootmall.dao;

import com.finn.springbootmall.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);
}
