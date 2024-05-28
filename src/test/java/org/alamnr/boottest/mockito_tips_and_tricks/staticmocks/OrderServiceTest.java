/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
 
/**
 *
 * @author Alam
 */
public class OrderServiceTest {

    private final OrderService cut = new OrderService();
    private final UUID defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020,1,1,12,0);

    @Test
    void shouldIncludeRandomOrderIdWhenNoParentOrderidExist(){
        try(MockedStatic<UUID> mockedUid = Mockito.mockStatic(UUID.class)){
            mockedUid.when(UUID::randomUUID).thenReturn(defaultUuid) ;
            
            Order result = cut.createOrder("Macbook Pro", 2L, null);

            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            assertEquals(defaultUuid.toString(),result.getId());
        }
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }

    @Test
    void shouldIncludeCurrentTimeWhenCreatingAOrder(){

        try(MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)){
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = cut.createOrder("Ipad", 3L, "123");

            assertEquals(defaultLocalDateTime,result.getCreationDate());
        }
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

 
}