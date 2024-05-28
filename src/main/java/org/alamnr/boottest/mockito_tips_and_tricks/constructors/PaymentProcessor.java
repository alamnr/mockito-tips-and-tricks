package org.alamnr.boottest.mockito_tips_and_tricks.constructors;

import java.math.BigDecimal;

public class PaymentProcessor {

    private boolean allowForeignCurrreny;
    private String fallBackOption;
    private BigDecimal taxRate;
    
    public PaymentProcessor() {
        this(false,"DEBIT",new BigDecimal("19.00"));
    }

    public PaymentProcessor(boolean allowForeignCurrreny, String fallBackOption, BigDecimal taxRate) {
        this.allowForeignCurrreny = allowForeignCurrreny;
        this.fallBackOption = fallBackOption;
        this.taxRate = taxRate;
    }

    public PaymentProcessor(String fallBackOption, BigDecimal taxRate) {
        this(false,fallBackOption,taxRate);
    }

    public BigDecimal chargeCustomer(String customerId,BigDecimal netPrice){
             // any arbitrary implementation
             System.out.println(" about to charge customer : "+ customerId);
             return BigDecimal.ZERO;
    }
    
    

}
