package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.CreateOrderRequest;
import com.finn.springbootmall.dto.OrderQueryParams;
import com.finn.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer order, CreateOrderRequest createOrderList);
}
