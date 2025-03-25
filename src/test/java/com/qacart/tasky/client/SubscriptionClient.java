package com.qacart.tasky.client;

import com.qacart.tasky.models.Card;
import com.qacart.tasky.models.User;
import com.qacart.tasky.utils.RoutesUtils;
import io.restassured.response.Response;

public final class SubscriptionClient {
    private SubscriptionClient(){}

    public static Response subscribeApi(Card card, String token){
        return
                BaseClient.getAuthRequestSpec(token)
                        .body(card)
                        .when().post("/subscriptions");
    }
}
