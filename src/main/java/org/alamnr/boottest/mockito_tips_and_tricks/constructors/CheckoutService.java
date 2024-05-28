package org.alamnr.boottest.mockito_tips_and_tricks.constructors;

import java.math.BigDecimal;

public class CheckoutService {
    public BigDecimal    purchaseProduct(String productName,String customerId){
        // any arbitrary implementation
        PaymentProcessor paymentProcessor = new PaymentProcessor( );
        return paymentProcessor.chargeCustomer(customerId, BigDecimal.TEN);
    }
}