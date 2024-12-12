package com.finn.springbootmall.service;

import com.finn.springbootmall.dao.ProductQueryParams;
import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams params);

    List<Product> getProducts(ProductQueryParams params);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
