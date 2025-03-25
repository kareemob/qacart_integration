package com.qacart.tasky.client;



import com.qacart.tasky.models.Todo;
import io.restassured.response.Response;

public final class TodoClient {
    private TodoClient(){}

    public static Response addTask(String token, Todo todo){

        return
                BaseClient.getAuthRequestSpec(token)
                        .body(todo)
                        .when()
                        .post("/todos");
    }

    public static Response editTask(String token, Todo todo){

        return
                BaseClient.getAuthRequestSpec(token)
                        .body(todo)
                        .when()
                        .post("/todos");
    }

    public static Response deleteTask(String token, String taskId){
        return
                BaseClient.getAuthRequestSpec(token)
                        .when().delete("/todos/" + taskId);
    }
}
