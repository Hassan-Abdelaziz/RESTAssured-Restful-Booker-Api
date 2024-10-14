package com.herokuapp.booker.restful;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Utils;


public class AuthenticationTest extends Utils {

    AuthenticationRequest request;

    @Test
    public void verifyValidAuthentication(){
    request = new AuthenticationRequest();

    Response response = request.AuthRequest("admin", "password123");
        // Extract token
        token = response.jsonPath().getString("token");

        // Assertions
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertTrue(response.getTime() < 2000 );
        System.out.println("Authentication successful. Token: " + token);
    }

    @Test
    public void verifyInvalidAPassword(){
        request = new AuthenticationRequest();

        Response response = request.AuthRequest("admin", "password1");

        // Assertions
        Assert.assertTrue(response.asString().contains("Bad credentials"));
        Assert.assertTrue(response.getTime() < 2000 );
        Assert.assertEquals(response.statusCode(), 401, "Expected status code 200");
        System.out.println("Invalid auth test passed with status: " + response.statusCode());
    }

    @Test
    public void verifyInvalidAUsername(){
        request = new AuthenticationRequest();

        Response response = request.AuthRequest("adin", "password123");

        // Assertions
        Assert.assertTrue(response.asString().contains("Bad credentials"));
        Assert.assertTrue(response.getTime() < 2000 );
        Assert.assertEquals(response.statusCode(), 401, "Expected status code 200");
        System.out.println("Invalid auth test passed with status: " + response.statusCode());
    }
}
