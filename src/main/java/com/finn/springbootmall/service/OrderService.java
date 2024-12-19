package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer order, CreateOrderRequest createOrderList);
}
