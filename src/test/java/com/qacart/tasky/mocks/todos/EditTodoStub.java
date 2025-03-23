package com.qacart.tasky.mocks.todos;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public final class EditTodoStub {
    private EditTodoStub(){}

    public static void updateTodo() {
        stubFor(
                patch(urlEqualTo(RoutesUtils.getGetTodosEndPoint() + "/6f41595e-6e8f-4fc1-8e8a-7d1da591b931"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todos.edit.mock.json"))
        );
    }
}
