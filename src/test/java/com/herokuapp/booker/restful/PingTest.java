package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class PingTest extends Utils {

    PingRequest request;

    @Test
    public void verifyOnline(){
        request = new PingRequest();

        Response response = request.pingRequest();

        // Assertions
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Created");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("We Are Online");
    }

}
