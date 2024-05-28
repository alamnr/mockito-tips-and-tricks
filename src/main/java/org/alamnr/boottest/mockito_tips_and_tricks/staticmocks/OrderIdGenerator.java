package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import java.util.UUID;

public class OrderIdGenerator {

    public String generateOrderId(){
        return UUID.randomUUID().toString();
    }
    
}
