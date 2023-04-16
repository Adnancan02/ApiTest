package testDatalari;

import org.json.JSONObject;

public class TestDataHerokuapp {
    public static int basariliSorguStatusCode=200;
    public static String contentType="application/json";

    public static JSONObject jsonRequestBodyOlustur(){
/*
"bookingid": 24, "booking": {
"firstname": "Ahmet",
"lastname": "Bulut",
"totalprice": 500,
"depositpaid": false,
"bookingdates": {
"checkin": "2021-06-01",
"checkout": "2021-06-10" },
"additionalneeds": "wi-fi" }}
 */
        JSONObject requestbody=new JSONObject();
        JSONObject bookingdatesBody=new JSONObject();
        bookingdatesBody.put("checkin","2021-06-01");
        bookingdatesBody.put("checkout","2021-06-10");
        requestbody.put("firstname","Ahmet");
        requestbody.put("lastname","Bulut");
        requestbody.put("totalprice",500);
        requestbody.put("depositpaid",false);
        requestbody.put("bookingdates",bookingdatesBody);
        requestbody.put("additionalneeds","wi-fi");
        return requestbody;
    }     /*
        {
"bookingid": 24,
"booking": {
"firstname": "Ahmet",
"lastname": "Bulut",
"totalprice": 500,
"depositpaid": false,
"bookingdates": {
"checkin": "2021-06-01",
"checkout": "2021-06-10" },
"additionalneeds": "wi-fi" }}
         */

        public static JSONObject jsonResponseBodyOlustur(){
            JSONObject responseBody=new JSONObject();
            JSONObject bookingBody=jsonRequestBodyOlustur();
            responseBody.put("bookingid",24);
            responseBody.put("booking",bookingBody);
            return responseBody;
        }

}
