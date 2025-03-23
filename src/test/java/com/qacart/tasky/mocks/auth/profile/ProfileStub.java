package com.qacart.tasky.mocks.auth.profile;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.awaitility.Awaitility.await;

public final class ProfileStub {
    private ProfileStub(){}

    public static void successProfileFree(){
        stubFor(
                get(urlEqualTo(RoutesUtils.getProfileEndPoint()))
                        .willReturn(aResponse()
                                .withBodyFile("profile.free.mock.json"))
        );
    }

    public static void successProfileAdvanced(){
        stubFor(
                get(urlEqualTo(RoutesUtils.getProfileEndPoint()))
                        .willReturn(aResponse()
                                .withBodyFile("profile.advanced.mock.json"))
        );
    }

    public static void successProfileUpdate(){
        stubFor(
                patch(urlEqualTo(RoutesUtils.getProfileEndPoint()))
                        .willReturn(aResponse()
                                .withBodyFile("profile.update.success.mock.json"))
        );
    }

    public static void failProfileUpdate(){
        stubFor(
                patch(urlEqualTo(RoutesUtils.getProfileEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withBodyFile("profile.update.fail.mock.json"))
        );
    }
}
