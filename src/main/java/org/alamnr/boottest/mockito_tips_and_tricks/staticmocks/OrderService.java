package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {
    
    public Order createOrder(String productName,Long amount,String parentOrderId){
        Order order = new Order();

        order.setId(parentOrderId == null ? UUID.randomUUID().toString() : parentOrderId);
        order.setProductName(productName);
        order.setCreationDate(LocalDateTime.now());
        order.setAmount(amount);

        return order;

    }
}
