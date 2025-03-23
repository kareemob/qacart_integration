package com.qacart.tasky.utils;

import java.util.UUID;

public final class DataUtils {
    private DataUtils(){}

    public static String generateUniqueEmail() {
        return "auto_test" + UUID.randomUUID().toString().replace("-","") + "@gmail.com";
    }

    public static String getPassword() {
        return "Test123!";
    }

    public static String getName(){
        return "Auto";
    }
    public static String getCardNumber(){
        return "333333333333";
    }
    public static String getCardExpiryMonth(){
        return "01";
    }
    public static String getCardExpiryYear(){
        return "2045";
    }
    public static String getCardCvv(){
        return "016";
    }
}
