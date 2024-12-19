package com.finn.springbootmall.dao;

import com.finn.springbootmall.dto.CreateOrderRequest;
import com.finn.springbootmall.model.Order;
import com.finn.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}