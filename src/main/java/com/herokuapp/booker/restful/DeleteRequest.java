package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteRequest {

    private String pathPar = "/booking/{id}";

    public Response deleteBooking(String id) {
        Response response;
        response =
                given()
                        .pathParam("id",id)
                        .auth().preemptive().basic("admin", "password123")
                .when()
                        .delete(pathPar)
                .then()
                        .extract().response();
        return response;
    }

}
