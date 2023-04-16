package tests;

import baseUrl.BaseUrlHerokuAppi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C20_BaseUrlHerokuapp2 extends BaseUrlHerokuAppi {

    @Test
    public void test01(){
        /*
        2- https://restful-booker.herokuapp.com/booking endpointine
        yandaki body’ye sahip bir POST request gonderdigimizde donen
        response’un status code’unun 200 oldugunu ve
         “firstname” degerinin “Ahmet” oldugunu test edin
         */
        //1-Endpint ve request olustur
        specHerokuapp.pathParam("pp1","booking");
        JSONObject requestBody=new JSONObject();
        JSONObject rezervasyonTarihleriJson=new JSONObject();
        rezervasyonTarihleriJson.put("checkin","2021-06-01");
        rezervasyonTarihleriJson.put("checkout","2021-06-10");
        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",rezervasyonTarihleriJson);
        requestBody.put("additionalneeds","wi-fi");

        //2-Expected data olustur

        //3-Response gonder ve donen response kaydet

        Response response=given().when().contentType(ContentType.JSON)
                .spec(specHerokuapp)
                .body(requestBody.toString()).post("/{pp1}");
        response.prettyPrint();
        //4-Assertion
        response.then().assertThat()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Ahmet"));
    }
}
