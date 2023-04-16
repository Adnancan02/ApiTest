package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C14_Post_ExpectedDataVeJsonPathIleAssertion {
    @Test
    public void test01(){
        /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.
Request body
{
"firstname" : "Ahmet",
"lastname" : “Bulut",
"totalprice" : 500,
"depositpaid" : false,
"bookingdates" : {
"checkin" : "2021-06-01",
"checkout" : "2021-06-10"
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
            "checkout": "2021-06-10"
        },
"additionalneeds": "wi-fi" }
}*/
//1- endpoint ve request body hazirla
        String url="https://restful-booker.herokuapp.com/booking";
        JSONObject requestBody=new JSONObject();
        JSONObject reztarihleriJson=new JSONObject();
        reztarihleriJson.put("checkin","2021-06-01");
        reztarihleriJson.put("checkout","2021-06-10");

        requestBody.put("firstname","Adnan");
        requestBody.put("lastname","Can");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",reztarihleriJson);
        requestBody.put("additionalneeds","wi-fi");
        System.out.println(requestBody.toString());


        //2-Expected data olustur

        JSONObject expectedDate=new JSONObject();
        expectedDate.put("bookingid",24);
        expectedDate.put("booking",requestBody);
        System.out.println(expectedDate.toString());

        //3- request gonderip kaydet
        Response response=given().contentType(ContentType.JSON).when()
                .body(requestBody.toString())
                .post(url);

        //4-Assertion
        JsonPath responseJsonPath=response.jsonPath();
        //Herzaman ilk yazilan expected ==> olusturdugumuz JSonObject : expectedData
        // ikinci actual  ===> response : responseJsonPath
        assertEquals(expectedDate.getJSONObject("booking").get("firstname"),responseJsonPath.get("booking.firstname"));
        assertEquals(expectedDate.getJSONObject("booking").get("lastname"),responseJsonPath.get("booking.lastname"));
        assertEquals(expectedDate.getJSONObject("booking").get("totalprice"),responseJsonPath.get("booking.totalprice"));
        assertEquals(expectedDate.getJSONObject("booking").get("depositpaid"),responseJsonPath.get("booking.depositpaid"));
        assertEquals(expectedDate.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));
        assertEquals(expectedDate.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                      responseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedDate.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                responseJsonPath.get("booking.bookingdates.checkout"));

    }
}
