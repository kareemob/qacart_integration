package com.qacart.tasky.mocks.subscriptions;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.awaitility.Awaitility.await;

public final class GetCurrentSubscriptionStateStub {
    private GetCurrentSubscriptionStateStub(){}

    public static void currentSubscribedMocked() {
        stubFor(
                get(urlEqualTo("/api/subscriptions/current"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBodyFile("subscriptions.get-current-state-advanced.json"))
        );
    }

    public static void currentSubscribedTwoDaysLeft() {
        stubFor(
                get(urlEqualTo("/api/subscriptions/current"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("subscriptions.current.2days.mock.json")
                        )
        );

        await().untilAsserted(() -> {
            verify(getRequestedFor(urlEqualTo("/api/subscriptions/current")));
        });
    }
}
