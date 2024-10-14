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

        Response response = request.updateRequest(caseBookingId,"Hasan","Abdel aziz",110,false,"2024-06-15","2024-08-25","dinner only");

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");

        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Hasan");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Abdel aziz");
        Assert.assertEquals((int) response.jsonPath().get("totalprice"), 110);
        Assert.assertFalse(response.jsonPath().getBoolean("depositpaid"));
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2024-06-15");
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "2024-08-25");

        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking updated successfully.");

    }




}
