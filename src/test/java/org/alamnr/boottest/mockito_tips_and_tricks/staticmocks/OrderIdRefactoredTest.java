package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderIdRefactoredTest {
    
    @Mock
    private  OrderIdGenerator  orderIdGenerator;
    @Mock
    private Clock clock;

    @InjectMocks
    private OrderServiceRefactored cut;

    @Test
    void testContext() {
        assertNotNull(cut);
    }

    @Test
    void shouldIncludeRandomIdAndCurrentDateTime(){
        when(orderIdGenerator.generateOrderId()).thenReturn("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
        LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020,1,1,12,0);
        Clock fixedClock = Clock.fixed(defaultLocalDateTime.toInstant(ZoneOffset.UTC),ZoneId.of("UTC"));
        when(clock.instant()).thenReturn(fixedClock.instant());
        when(clock.getZone()).thenReturn(fixedClock.getZone());

        Order result = cut.creatOrder("Iphone", 600L, null);

        assertEquals(defaultLocalDateTime, result.getCreationDate());
        assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.getId());
        
    }
}
