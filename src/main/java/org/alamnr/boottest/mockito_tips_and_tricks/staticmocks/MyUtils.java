package org.alamnr.boottest.mockito_tips_and_tricks.staticmocks;

public class MyUtils {
    
    public static String getWelcomeMessage(String userName, boolean isCustomer){
        if(isCustomer){
                return " Dear "+ userName ;
        } else {
            return "Hello " + userName ;
        }
    }
}
