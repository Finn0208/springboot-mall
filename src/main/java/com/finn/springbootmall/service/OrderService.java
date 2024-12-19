package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.CreateOrderRequest;
import com.finn.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer order, CreateOrderRequest createOrderList);
}
