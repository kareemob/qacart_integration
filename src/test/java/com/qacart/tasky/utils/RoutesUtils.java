package com.qacart.tasky.utils;



public final class RoutesUtils {
    private RoutesUtils(){}

    public static String getApiRegisterEndPoint() {
        return "/api/auth/register";
    }

    public static String getProfileEndPoint() {
        return "/api/auth/profile";
    }

    public static String getLoginEndPoint() {
        return "/api/auth/login";
    }

    public static String getGetTodosEndPoint() {
        return "/api/todos";
    }

    public static String getGetSubscriptionPlansEndPoint() {
        return "/api/subscription-types";
    }

    public static String getSubscribeEndPoint() {
        return "/api/subscriptions";
    }

    public static String getGetCurrentSubscriptionState() {
        return "/api/subscriptions/current";
    }

    public static String getCancelSubscriptionEndPoint() {
        return "/api/subscriptions/cancel";
    }
}
