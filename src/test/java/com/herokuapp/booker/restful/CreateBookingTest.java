package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class CreateBookingTest extends Utils {

    CreateBookingRequest request;

    @Test
    public void verifyValidCreation(){
        request = new CreateBookingRequest();

        Response response = request.createRequest("Hassan","Abdelaziz",180,true,"2024-05-15","2024-11-25","dinner");

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");

        // Extract bookingId and check that it is not null
        bookingId = response.jsonPath().getString("bookingid");
        Assert.assertNotNull(caseBookingId, "Booking ID should not be null");

        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Hassan");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Abdelaziz");
        Assert.assertEquals((int) response.jsonPath().get("booking.totalprice"), 180);
        Assert.assertTrue(response.jsonPath().getBoolean("booking.depositpaid"));
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), "2024-05-15");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), "2024-11-25");
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), "dinner");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking created successfully. ID: " + caseBookingId);

    }

    @Test
    public void verifyInvalidCreationFirstNameNotString(){
        request = new CreateBookingRequest();
        String body = """
                {
                  "firstname": Hassan,
                  "lastname": "Abdelaziz",
                  "totalprice": 150,
                  "depositpaid": true,
                  "bookingdates": \
                {
                    "checkin": "2024-09-09",
                    "checkout": "2024-09-16"
                },
                  "additionalneeds": "Breakfast"
                }""";

        Response response = request.createRequest(body);

        // Assertions
        Assert.assertEquals(response.statusCode(), 400, "Expected status code 400");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Failed to create booking Error Code: " + response.statusCode());
    }

    @Test
    public void verifyInvalidCreationLastNameNotString(){
        request = new CreateBookingRequest();
        String body = """
                {
                  "firstname": "Hassan",
                  "lastname": Abdelaziz,
                  "totalprice": 150,
                  "depositpaid": true,
                  "bookingdates": \
                {
                    "checkin": "2024-09-09",
                    "checkout": "2024-09-16"
                },
                  "additionalneeds": "Breakfast"
                }""";

        Response response = request.createRequest(body);

        // Assertions
        Assert.assertEquals(response.statusCode(), 400, "Expected status code 400");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Failed to create booking Error Code: " + response.statusCode());
    }

    @Test
    public void verifyInvalidCreationTotalPriceBoolean(){
        request = new CreateBookingRequest();
        String body = """
                {
                  "firstname": "Hassan",
                  "lastname": "Abdelaziz",
                  "totalprice": true,
                  "depositpaid": true,
                  "bookingdates": \
                {
                    "checkin": "2024-09-09",
                    "checkout": "2024-09-16"
                },
                  "additionalneeds": "Breakfast"
                }""";

        Response response = request.createRequest(body);

        // Assertions
        Assert.assertEquals(response.statusCode(), 500, "Booking should not be created");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Failed to create booking Error Code: " + response.statusCode());
    }

    @Test
    public void verifyInvalidCreationDateInteger(){
        request = new CreateBookingRequest();
        String body = """
                {
                  "firstname": "Hassan",
                  "lastname": "Abdelaziz",
                  "totalprice": true,
                  "depositpaid": true,
                  "bookingdates": \
                {
                    "checkin": 2024-09-09,
                    "checkout": "2024-09-16"
                },
                  "additionalneeds": "Breakfast"
                }""";

        Response response = request.createRequest(body);

        // Assertions
        Assert.assertEquals(response.statusCode(), 400, "Expected status code 400");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Failed to create booking Error Code: " + response.statusCode());
    }

}
