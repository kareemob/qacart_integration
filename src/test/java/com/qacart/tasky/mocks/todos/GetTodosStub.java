package com.qacart.tasky.mocks.todos;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class GetTodosStub {
    private GetTodosStub(){}

    public static void emptyTodo() {
        stubFor(
                get(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint()))
                        .withQueryParam("page", equalTo("1"))
                        .withQueryParam("limit", equalTo("5"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.empty.json"))
        );
    }

    public static void fullTodosForFreeUser(){
        stubFor(
                get(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint()))
                        .withQueryParam("page", equalTo("1"))
                        .withQueryParam("limit", equalTo("5"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.full.free.mock.json"))
        );
    }

    public static void moreThan3Todos(){
        stubFor(
                get(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint()))
                        .withQueryParam("page", equalTo("1"))
                        .withQueryParam("limit", equalTo("5"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.get.adv.mock.json"))
        );
    }

    public static void getTodoAfterEdit(){
        stubFor(
                get(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint()))
                        .withQueryParam("page", equalTo("1"))
                        .withQueryParam("limit", equalTo("5"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.after.edit.mock.json"))
        );
    }
}
