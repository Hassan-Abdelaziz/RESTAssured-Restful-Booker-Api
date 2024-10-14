package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;

public class EndToEndTest extends Utils {

    PingRequest pingRequest;
    AuthenticationRequest authRequest;
    CreateBookingRequest createRequest;
    GetIDsRequest getIDsRequest;
    GetDetailsByIDsRequest getDetailsRequest;
    UpdateBookingRequest updateRequest;
    DeleteRequest deleteRequest;

    @Test
    public void verifyEndToEndValidScenario(){
        pingRequest = new PingRequest();
        Response response = pingRequest.pingRequest();
        // Assertions
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Created");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("We Are Online");


        authRequest = new AuthenticationRequest();
        response = authRequest.AuthRequest("admin", "password123");
        // Extract token
        token = response.jsonPath().getString("token");
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Authentication successful. Token: " + token);


        createRequest = new CreateBookingRequest();
        response = createRequest.createRequest("Hassan","Abdelaziz",180,true,"2024-05-15","2024-11-25","dinner");
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Extract bookingId and check that it is not null
        bookingId = response.jsonPath().getString("bookingid");
        Assert.assertNotNull(bookingId, "Booking ID should not be null");
        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Hassan");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Abdelaziz");
        Assert.assertEquals((int) response.jsonPath().get("booking.totalprice"), 180);
        Assert.assertTrue(response.jsonPath().getBoolean("booking.depositpaid"));
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), "2024-05-15");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), "2024-11-25");
        Assert.assertEquals(response.jsonPath().getString("booking.additionalneeds"), "dinner");
        Assert.assertTrue(response.getTime() < 2000 );
        // Extract Variables
        firstname = response.jsonPath().getString("booking.firstname");
        lastname = response.jsonPath().getString("booking.lastname");
        totalprice = response.jsonPath().getString("booking.totalprice");
        depositpaid = response.jsonPath().getString("booking.depositpaid");
        checkin = response.jsonPath().getString("booking.bookingdates.checkin");
        checkout = response.jsonPath().getString("booking.bookingdates.checkout");
        additionalneeds = response.jsonPath().getString("booking.additionalneeds");
        System.out.println("Booking created successfully. ID: " + bookingId);


        getIDsRequest = new GetIDsRequest();
        response = getIDsRequest.getBookingIDsRequest();
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Validate that the response contains bookingid
        Assert.assertTrue(!response.jsonPath().getList("..").isEmpty(), "Response should not be empty");
        // Validate that bookingID added to IDs
        Assert.assertEquals(response.jsonPath().getString("bookingid").contains(bookingId), true);
        // Check that the first bookingid is not null
        Assert.assertNotNull(response.jsonPath().getString("bookingid[0]"), "Booking ID should not be null");
        // Data type validation for bookingid
        Assert.assertTrue(response.jsonPath().get("bookingid[0]") instanceof Integer, "Booking ID should be a number");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking added to IDs successfully.");


        getDetailsRequest = new GetDetailsByIDsRequest();
        response = getDetailsRequest.getBookingDetailsByIDRequest(bookingId);
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), firstname);
        Assert.assertEquals(response.jsonPath().getString("lastname"), lastname);
        Assert.assertEquals(response.jsonPath().getString("totalprice"), totalprice);
        Assert.assertEquals(response.jsonPath().getString("depositpaid"), depositpaid);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), checkin);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), checkout);
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), additionalneeds);
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking Details retrieved successfully.");


        updateRequest = new UpdateBookingRequest();
        response = updateRequest.updateRequest(bookingId,"Hasan","Abd elaziz",130,false,"2024-05-17","2024-5-25","lunch and dinner");
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Update Variables
        firstname = response.jsonPath().getString("firstname");
        lastname = response.jsonPath().getString("lastname");
        totalprice = response.jsonPath().getString("totalprice");
        depositpaid = response.jsonPath().getString("depositpaid");
        checkin = response.jsonPath().getString("bookingdates.checkin");
        checkout = response.jsonPath().getString("bookingdates.checkout");
        additionalneeds = response.jsonPath().getString("additionalneeds");
        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), firstname);
        Assert.assertEquals(response.jsonPath().getString("lastname"), lastname);
        Assert.assertEquals(response.jsonPath().getString("totalprice"), totalprice);
        Assert.assertEquals(response.jsonPath().getString("depositpaid"), depositpaid);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), checkin);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), checkout);
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), additionalneeds);
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking updated successfully.");


        getDetailsRequest = new GetDetailsByIDsRequest();
        response = getDetailsRequest.getBookingDetailsByIDRequest(bookingId);
        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        // Validate values in the booking object
        Assert.assertEquals(response.jsonPath().getString("firstname"), firstname);
        Assert.assertEquals(response.jsonPath().getString("lastname"), lastname);
        Assert.assertEquals(response.jsonPath().getString("totalprice"), totalprice);
        Assert.assertEquals(response.jsonPath().getString("depositpaid"), depositpaid);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), checkin);
        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), checkout);
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), additionalneeds);
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Booking Details updated and retrieved successfully.");


        deleteRequest = new DeleteRequest();
        response = deleteRequest.deleteBooking(bookingId);
        // Assertions
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Created");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Deleted");


        getDetailsRequest = new GetDetailsByIDsRequest();
        response = getDetailsRequest.getBookingDetailsByIDRequest("99999");
        // Assertions
        Assert.assertEquals(response.statusCode(), 404, "Expected status code 404");
        // Validate values in the booking object
        Assert.assertEquals(response.asString(), "Not Found");
        // Assert response time
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Not Found");



    }
}
