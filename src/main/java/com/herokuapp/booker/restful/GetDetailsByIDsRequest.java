package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetDetailsByIDsRequest {

        private String pathPar = "/booking/{id}";


        public Response getBookingDetailsByIDRequest(String id){
            Response response;
            response=
                    given()
                            .pathParam("id",id)
                    .when()
                        .get(pathPar)
                    .then()
                        .extract().response();
            return response;
        }

}
