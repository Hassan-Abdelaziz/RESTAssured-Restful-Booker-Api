package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class UpdateBookingTest extends Utils {

    UpdateBookingRequest request;

    @Test
    public void verifyValidUpdate(){
        request = new UpdateBookingRequest();

        Response response = request.updateRequest("1","Hassan","Abdelaziz",180,true,"2024-05-15","2024-11-25","dinner");

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");

        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Hassan");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Abdelaziz");
        Assert.assertEquals((int) response.jsonPath().get("totalprice"), 180);
        Assert.assertTrue(response.jsonPath().getBoolean("depositpaid"));
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2024-05-15");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2024-11-25");

        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking updated successfully.");

    }




}
