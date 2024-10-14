package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class DeleteTest extends Utils {

    DeleteRequest request;

    @Test
    public void verifyValidDeleteID(){
        request = new DeleteRequest();

        Response response = request.deleteBooking(caseBookingId);

        // Assertions
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Created");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Deleted");
    }

    @Test
    public void verifyInvalidDeleteID(){
        request = new DeleteRequest();

        Response response = request.deleteBooking("99999");

        // Assertions
        Assert.assertEquals(response.statusCode(), 405, "Expected status code 405");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Method Not Allowed");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Method Not Allowed");
    }

}
