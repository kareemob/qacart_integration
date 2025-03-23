package com.qacart.tasky.mocks.auth.login;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class LoginStub {
    private LoginStub(){}


    public static void successLogin(){
        stubFor(
                post(urlEqualTo(RoutesUtils.getLoginEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBodyFile("login.mock.json")
                        )
        );
    }

    public static void unAuthorizedLogin(){
        stubFor(
                post(urlEqualTo(RoutesUtils.getLoginEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(401)
                                .withBodyFile("login.invalid.mock.json")
                        )
        );
    }
}
