package com.herokuapp.booker.restful;

public class Payload {

    public String authPayload(String username, String password){
       return  "{\n" +
                "  \"username\": \""+username+"\",\n" +
                "  \"password\": \""+password+"\"\n" +
                "}";
    }

    public String createPayload(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds){
        return  "{\n" +
                "  \"firstname\": \""+firstname+"\",\n" +
                "  \"lastname\": \""+lastname+"\",\n" +
                "  \"totalprice\": "+totalprice+",\n" +
                "  \"depositpaid\": "+depositpaid+",\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \""+checkin+"\",\n" +
                "    \"checkout\": \""+checkout+"\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \""+additionalneeds+"\"\n" +
                "}";
    }

    public String updatePayload(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds){
        return  "{\n" +
                "  \"firstname\": \""+firstname+"\",\n" +
                "  \"lastname\": \""+lastname+"\",\n" +
                "  \"totalprice\": "+totalprice+",\n" +
                "  \"depositpaid\": "+depositpaid+",\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \""+checkin+"\",\n" +
                "    \"checkout\": \""+checkout+"\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \""+additionalneeds+"\"\n" +
                "}";
    }

}
