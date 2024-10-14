package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetIDsRequest {

    private String pathPar = "/booking";

    public Response getBookingIDsRequest() {
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
