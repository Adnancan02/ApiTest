package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Put_DeSerialization extends BaseUrlJsonPlaceholder {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
    PUT request yolladigimizda donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz

Request Body                          Expected Data :
{                                        {
 "title": "Ahmet",                      "title": "Ahmet",
"body": "Merhaba",                       "body": "Merhaba",
"userId": 10,                             "userId": 10,
"id": 70                                   "id": 70
}                                             }   */
    @Test
public void test01(){
    //1- Endpoint ve request body olustur
    specJsonPlaceholder.pathParams("pp1","posts","pp2",70);
    Map<String,Object> requestBodyMap= TestDataJsonPlaceholder.bodyOlusturMap();
   // System.out.println("Request Body Map : "+requestBodyMap);

    //2-Soruda varsa expected data olustur
Map<String,Object> expectedData=TestDataJsonPlaceholder.bodyOlusturMap();
    //3-Request gonder donen response'i kaydet

    Response response=given().spec(specJsonPlaceholder).when()
            .body(requestBodyMap)
            .contentType(ContentType.JSON)
            .put("{pp1}/{pp2}");
   // response.prettyPrint();
    //4-Assertion
    //Expected Response body <==>response
    //Map                        Response

        //Assertion yapabilmemiz icin response'i Map'e cevirmemiz gerekir.(De-Serialization)
        Map<String,Object> responseMap=response.as(HashMap.class);
        //System.out.println(responseMap);

        //expectedData (MAp)-----response(Map)
        assertEquals(expectedData.get("title"),responseMap.get("title"));
        assertEquals(expectedData.get("body"),responseMap.get("body"));
        assertEquals(expectedData.get("userId"),responseMap.get("userId"));
        assertEquals(expectedData.get("id"),responseMap.get("id"));
}


}
