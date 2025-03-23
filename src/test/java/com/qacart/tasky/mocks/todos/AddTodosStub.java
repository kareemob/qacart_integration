package com.qacart.tasky.mocks.todos;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class AddTodosStub {
    private AddTodosStub(){}

    public static void addNewTodo() {
        stubFor(
                post(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint()))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.add.mock.json"))
        );
    }
}
