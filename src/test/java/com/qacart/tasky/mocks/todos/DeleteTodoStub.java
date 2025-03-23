package com.qacart.tasky.mocks.todos;

import com.qacart.tasky.utils.RoutesUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class DeleteTodoStub {
    private DeleteTodoStub(){}

    public static void deleteTodo() {
        stubFor(
                delete(urlPathEqualTo(RoutesUtils.getGetTodosEndPoint() + "/6f41595e-6e8f-4fc1-8e8a-7d1da591b931"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("todo.delete.mock.json"))
        );
    }
}
