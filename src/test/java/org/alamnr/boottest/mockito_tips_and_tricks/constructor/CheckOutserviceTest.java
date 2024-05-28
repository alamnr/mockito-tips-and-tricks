package org.alamnr.boottest.mockito_tips_and_tricks.constructor;

import java.math.BigDecimal;

import org.alamnr.boottest.mockito_tips_and_tricks.constructors.CheckoutService;
import org.alamnr.boottest.mockito_tips_and_tricks.constructors.PaymentProcessor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class CheckOutserviceTest {

  @Test
  void mockObjectConstruction() {
    try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class,
        (mock, context) -> {
          // further stubbings ...
          when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
        })) {
      CheckoutService cut = new CheckoutService();

      BigDecimal result = cut.purchaseProduct("MacBook Pro", "42");

      assertEquals(BigDecimal.TEN, result);
      assertEquals(1, mocked.constructed().size());
    }
  }
}