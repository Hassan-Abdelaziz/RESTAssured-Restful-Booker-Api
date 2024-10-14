package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PingRequest {

    private String pathPar = "/ping";

    public Response pingRequest() {
        Response response;
        response =
                given()
                .when()
                        .get(pathPar)
                .then()
                        .extract().response();
        return response;
    }

}
