package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class GetIDsTest extends Utils {

    GetIDsRequest request;

    @Test
    public void verifyValidGetIDs(){
        request = new GetIDsRequest();

        Response response = request.getBookingIDsRequest();

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Validate that the response contains bookingid
        Assert.assertTrue(!response.jsonPath().getList("..").isEmpty(), "Response should not be empty");
        // Check that the first bookingid is not null
        Assert.assertNotNull(response.jsonPath().getString("bookingid[0]"), "Booking ID should not be null");
        // Data type validation for bookingid
        Assert.assertTrue(response.jsonPath().get("bookingid[0]") instanceof Integer, "Booking ID should be a number");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking IDs retrieved successfully.");
    }

}
