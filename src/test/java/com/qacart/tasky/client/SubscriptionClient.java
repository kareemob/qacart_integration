package com.qacart.tasky.client;

import com.qacart.tasky.models.Card;
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
