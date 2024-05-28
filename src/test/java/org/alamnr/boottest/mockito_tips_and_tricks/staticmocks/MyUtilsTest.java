package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import org.alamnr.boottest.mockito_tips_and_tricks.staticmocks.MyUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MyUtilsTest {

    @Test
    void shouldMockStaticMethod(){

        try(MockedStatic<MyUtils> mockedStatic = Mockito.mockStatic(MyUtils.class)) {
            // mockedStatic.when(() -> MyUtils.getWelcomeMessage(anyString(), anyBoolean()))
            // .thenReturn("Howdy duke");

            mockedStatic.when(() -> MyUtils.getWelcomeMessage("duke", false))
            .thenReturn("Howdy puke");

            String result = MyUtils.getWelcomeMessage("duke", false);
            assertEquals("Howdy duke", result);
        }
    }
    
}
