package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C23_Get_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {

    @Test
    public void test02(){


    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen response’in
    status kodunun 200 ve response body’sinin asagida verilen ile ayni oldugunutest ediniz

Response body :
{
    "userId": 3,
"id": 22,
"title": "dolor sint quo a velit explicabo quia nam",
"body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}
um mollitia molestiae aut atque rem suscipit\nnam impedit esse"

     */
    //1-Endpoint ve request gonder
    specJsonPlaceholder.pathParams("pp1","posts","pp2","22");
    //2-Expected data olustur

        JSONObject expectedData= TestDataJsonPlaceholder.responseBodyOlustur22();
        System.out.println(expectedData);

    //3-Request gonder ve donen response kaydet
        Response response=given().when().spec(specJsonPlaceholder).get("/{pp1}/{pp2}");
response.prettyPrint();
        JsonPath responseJsonpath=response.jsonPath();
    //4-Assertion

       // response.then().assertThat().statusCode(TestDataJsonPlaceholder.basariliSorguStatusCode);
        Assert.assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(expectedData.getInt("userId"),responseJsonpath.getInt("userId"));
        Assert.assertEquals(expectedData.getString("title"),responseJsonpath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"),responseJsonpath.getString("body"));
}
}