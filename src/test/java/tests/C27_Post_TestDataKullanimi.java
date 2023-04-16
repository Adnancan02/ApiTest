package tests;

import baseUrl.BaseUrlHerokuAppi;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataDummyExample;
import testDatalari.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Post_TestDataKullanimi extends BaseUrlHerokuAppi {
    @Test
    public void test01(){

        /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki
        body'ye sahip bir POST request gonderdigimizde
        donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
{
"firstname" : "Ahmet",
 "lastname" : “Bulut",
 "totalprice" : 500,
 "depositpaid" : false,
 "bookingdates" : {
"checkin" : "2021-06-01",
"checkout" : "2021-06-10" },
"additionalneeds" : "wi-fi" }
Response Body
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

        //1-Endpoint ve request gonder
 specHerokuapp.pathParam("pp1","booking");
 JSONObject requestBody= TestDataHerokuapp.jsonRequestBodyOlustur();
        System.out.println(requestBody);
        //2-Expected data olustur
JSONObject expectedData=TestDataHerokuapp.jsonResponseBodyOlustur();
        System.out.println(requestBody);

        // System.out.println(expectedData);
        //3-Request gonder ve donen response kaydet
Response response=given().spec(specHerokuapp).contentType(ContentType.JSON)
        .when().body(requestBody.toString())
        .post("{pp1}");
response.prettyPrint();

        //4-Assertion
        //Expected daat :JSONObject
        //response :JSonPath
        JsonPath responseJP=response.jsonPath();
       assertEquals(expectedData.getJSONObject("booking").getString("firstname"),
                responseJP.getString("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),
                responseJP.getString("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),
                responseJP.getString("booking.additionalneeds"));
        assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),
                responseJP.getBoolean("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),
                responseJP.getInt("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),
                responseJP.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),
                responseJP.getString("booking.bookingdates.checkout"));


    }
}
