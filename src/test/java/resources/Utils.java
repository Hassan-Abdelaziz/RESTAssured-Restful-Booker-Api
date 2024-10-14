package resources;

import io.restassured.RestAssured;

public class Utils {

    private  String baseUri = "https://restful-booker.herokuapp.com";
    public static String token;
    public static String caseBookingId;
    public static String bookingId;
    public static String firstname;
    public static String lastname;
    public static String totalprice;
    public static String depositpaid;
    public static String checkin;
    public static String checkout;
    public static String additionalneeds;


     public Utils(){
        RestAssured.baseURI= baseUri;
    }

}
