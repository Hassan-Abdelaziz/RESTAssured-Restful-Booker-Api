package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UpdateBookingRequest {

    private String pathPar = "/booking/{id}" ;

    public Response updateRequest(String id, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds){

        Payload payload = new Payload();

        Response response;
        response =
                given()
                        .pathParam("id",id)
                        .auth().preemptive().basic("admin", "password123")
                        .header("Content-Type", "application/json")
                        .body(payload.updatePayload(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds))
                .when()
                        .put(pathPar)
                .then()
                        .extract().response();
        return response;
    }






    }
