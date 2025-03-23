package com.qacart.tasky.mocks.subscriptions;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class CancelSubscriptionStub {
    private CancelSubscriptionStub(){}

    public static void cancelSubscription() {
        stubFor(
                post(urlEqualTo(RoutesUtils.getCancelSubscriptionEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("subscriptions.subscribe.mock.json"))
        );
    }
}
