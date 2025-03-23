package com.qacart.tasky.mocks.auth.signup;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class RegisterStub {
    private RegisterStub(){}

    public static void successRegisterFree(){
        stubFor(
                post(urlEqualTo(RoutesUtils.getApiRegisterEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withBodyFile("register.free.mock.json")
                        )
        );
    }

    public static void registerWithExistUser(){
        stubFor(
                post(urlEqualTo(RoutesUtils.getApiRegisterEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withBodyFile("register.already.exist.mock.json")
                        )
        );
    }
}
