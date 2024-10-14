package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthenticationRequest {

    private String pathPar = "/auth" ;

    public Response AuthRequest(String username, String password){

        Payload payload = new Payload();

        Response response;
        response =
                given()
                       .header("Content-Type", "application/json")
                       .body(payload.authPayload(username, password))
                .when()
                       .post(pathPar)
                .then()
                       .extract().response();
        return response;
    }

}
