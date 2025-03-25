package com.qacart.tasky.client;



import com.qacart.tasky.models.User;
import io.restassured.response.Response;

public final class UserClient {
    private UserClient(){}

    public static Response registerApi(User user){
        return
                BaseClient.getRequestSpec()
                        .body(user)
                        .when().post("/auth/register");

    }

    public static Response loginApi(User user){
        return
                BaseClient.getRequestSpec()
                        .body(user)
                        .when().post("/auth/login");

    }
}
