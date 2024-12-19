package com.finn.springbootmall.dao;

import com.finn.springbootmall.dto.CreateOrderRequest;
import com.finn.springbootmall.dto.OrderQueryParams;
import com.finn.springbootmall.model.Order;
import com.finn.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
