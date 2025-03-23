package com.qacart.tasky.utils;

import lombok.Getter;


public final class RoutesUtils {
    private RoutesUtils(){}
    private static String API_REGISTER_END_POINT = "/api/auth/register";
    private static String PROFILE_END_POINT = "/api/auth/profile";
    private static String LOGIN_END_POINT = "/api/auth/login";
    private static String GET_TODOS_END_POINT = "/api/todos";
    private static String GET_SUBSCRIPTION_PLANS_END_POINT = "/api/subscription-types";
    private static String SUBSCRIBE_END_POINT = "/api/subscriptions";
    private static String GET_CURRENT_SUBSCRIPTION_STATE = "/api/subscriptions/current";
    private static String CANCEL_SUBSCRIPTION_END_POINT = "/api/subscriptions/cancel";

    public static String getApiRegisterEndPoint() {
        return API_REGISTER_END_POINT;
    }

    public static String getProfileEndPoint() {
        return PROFILE_END_POINT;
    }

    public static String getLoginEndPoint() {
        return LOGIN_END_POINT;
    }

    public static String getGetTodosEndPoint() {
        return GET_TODOS_END_POINT;
    }

    public static String getGetSubscriptionPlansEndPoint() {
        return GET_SUBSCRIPTION_PLANS_END_POINT;
    }

    public static String getSubscribeEndPoint() {
        return SUBSCRIBE_END_POINT;
    }

    public static String getGetCurrentSubscriptionState() {
        return GET_CURRENT_SUBSCRIPTION_STATE;
    }

    public static String getCancelSubscriptionEndPoint() {
        return CANCEL_SUBSCRIPTION_END_POINT;
    }
}
