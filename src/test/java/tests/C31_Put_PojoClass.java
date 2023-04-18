package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C31_Put_PojoClass extends BaseUrlJsonPlaceholder {

    @Test
    public void test(){

        /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request yolladigimizda
        donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body                Expected Data :
{                                  {
"title": "Ahmet",             "title": "Ahmet",
"body": "Merhaba",             "body": "Merhaba",
"userId": 10,                   "userId": 10,
"id": 70 }                        "id": 70 }
         */
        // 1- request url ve body olustur
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);
        PojoJsonPlaceholder requestBodyPojo=
                new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);


        // 2- soruda varsa expected data olustur
        PojoJsonPlaceholder expectedDataPojo=new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);
        //3-Response olustur, request gonderip sonucu response'a ata
        Response response=given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                .when().body(requestBodyPojo)
                .put("{pp1}/{pp2}");
       // response.prettyPrint();
        PojoJsonPlaceholder responsePojo=response.as(PojoJsonPlaceholder.class);
        //4-Assertion
        //expected data (pojo) <===>response (Pojo yapilmali)
        //expectedDataPojo              reponsePojo
        //status kodunun 200,
        Assert.assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode,response.statusCode());
       //content type'nin "application/json; charset=utf-8"
       assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());
        //Connection header degerinin "keep-alive"
        assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));

        //response body’sinin asagida verilen ile ayni oldugunu test ediniz
        assertEquals(expectedDataPojo.getTitle(),requestBodyPojo.getTitle());
        assertEquals(expectedDataPojo.getBody(),requestBodyPojo.getBody());
        assertEquals(expectedDataPojo.getUserId(),requestBodyPojo.getUserId());
        assertEquals(expectedDataPojo.getId(),requestBodyPojo.getId());


    }
}
