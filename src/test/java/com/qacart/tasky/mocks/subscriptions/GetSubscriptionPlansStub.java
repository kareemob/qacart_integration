package com.qacart.tasky.mocks.subscriptions;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class GetSubscriptionPlansStub {
    private GetSubscriptionPlansStub(){}

    public static void getSubscriptionPlansMocked() {
        stubFor(
                get(urlEqualTo(RoutesUtils.getGetSubscriptionPlansEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("subscriptions.get-plans-free-user.json"))
        );
    }
}
