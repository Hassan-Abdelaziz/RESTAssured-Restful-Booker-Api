package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class GetDetailsByIDsTest extends Utils {

    GetDetailsByIDsRequest request;

    @Test
    public void verifyValidGetBookingDetailsByID(){
        request = new GetDetailsByIDsRequest();

        Response response = request.getBookingDetailsByIDRequest(caseBookingId);

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Hassan");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Abdelaziz");
        Assert.assertEquals((int) response.jsonPath().get("totalprice"), 180);
        Assert.assertTrue(response.jsonPath().getBoolean("depositpaid"));
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2024-05-15");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2024-11-25");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), "dinner");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking Details retrieved successfully.");
    }

    @Test
    public void verifyInvalidGetBookingDetailsByNonExistID(){
        request = new GetDetailsByIDsRequest();

        Response response = request.getBookingDetailsByIDRequest("99999");

        // Assertions
        Assert.assertEquals(response.statusCode(), 404, "Expected status code 404");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Not Found");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Not Found");
    }

}
