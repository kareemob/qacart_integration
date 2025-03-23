package com.qacart.tasky.mocks.subscriptions;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class SubscribeStub {
    private SubscribeStub(){}

    public static void subscribeMocked() {
        stubFor(
                post(urlEqualTo(RoutesUtils.getSubscribeEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("subscriptions.subscribe.mock.json"))
        );
    }
}
