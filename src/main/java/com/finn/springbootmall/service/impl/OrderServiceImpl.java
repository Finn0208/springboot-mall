package com.finn.springbootmall.service.impl;

import com.finn.springbootmall.dao.OrderDao;
import com.finn.springbootmall.dao.ProductDao;
import com.finn.springbootmall.dto.BuyItem;
import com.finn.springbootmall.dto.CreateOrderRequest;
import com.finn.springbootmall.model.Order;
import com.finn.springbootmall.model.OrderItem;
import com.finn.springbootmall.model.Product;
import com.finn.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmout = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總金額
            int amount = buyItem.getQuantity()* product.getPrice();
            totalAmout = totalAmout+amount;

            // 轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmout);

        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }
}
