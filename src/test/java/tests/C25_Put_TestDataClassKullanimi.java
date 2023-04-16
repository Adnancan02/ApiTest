package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {
    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request
        yolladigimizda donen response’in
status kodunun 200, content type’inin “application/json; charset=utf-8”, Connection header degerinin “keep-alive”
ve response body’sinin asagida verilen ile ayni oldugunu test ediniz


{ "title": "Ahmet",
"body": "Merhaba",
"userId": 10,
"id": 70 }
}
"title": "Ahmet",
 "body": "Merhaba",
  "userId": 10,
"id": 70
         */

        //1-Endpoint ve request gonder
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        JSONObject requestBody=TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Ahmet","Merhaba");

        //2-Expected data olustur

        JSONObject expectedData= TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Ahmet","Merhaba");

        //3-Request gonder ve donen response kaydet
        Response response=given().spec(specJsonPlaceholder).when().body(requestBody.toString()).contentType(ContentType.JSON).put("{pp1}/{pp2}");
        response.prettyPrint();

        JsonPath responseJsonpath=response.jsonPath();
        //4-Assertion
       assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode,response.statusCode());

        assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());
        assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));
        assertEquals(expectedData.getInt("userId"),responseJsonpath.getInt("userId"));
        assertEquals(expectedData.getInt("id"),responseJsonpath.getInt("id"));
        assertEquals(expectedData.getString("title"),responseJsonpath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonpath.getString("body"));




    }
}
