package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import java.time.Clock;
import java.time.LocalDateTime;

public class OrderServiceRefactored {

    private final Clock clock;
    private final OrderIdGenerator generator;
    public OrderServiceRefactored(Clock clock, OrderIdGenerator generator) {
        this.clock = clock;
        this.generator = generator;
    }

    public Order creatOrder(String productName,Long amount, String parentOrderId){
        Order order = new Order();
        order.setId(parentOrderId == null ? generator.generateOrderId() : parentOrderId);
        order.setCreationDate(LocalDateTime.now(clock));
        order.setAmount(amount);
        order.setProductName(productName);

        return order;
    }
    
}
