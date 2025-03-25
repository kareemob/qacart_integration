package com.qacart.tasky.client;



import com.qacart.tasky.config.ConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class BaseClient {
    private BaseClient(){}

    public static RequestSpecification getRequestSpec(){
        return
                given()
                        .baseUri(ConfigFactory.getConfig().apiUri())
                        .contentType(ContentType.JSON);

    }

    public static RequestSpecification getAuthRequestSpec(String token){
        return
                getRequestSpec().auth().oauth2(token);


    }
}
