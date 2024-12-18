package com.finn.springbootmall.service.impl;

import com.finn.springbootmall.dao.ProductDao;
import com.finn.springbootmall.dao.ProductQueryParams;
import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;
import com.finn.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProduct(ProductQueryParams params) {
        return productDao.countProduct(params);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams params) {
        return productDao.getProducts(params);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
