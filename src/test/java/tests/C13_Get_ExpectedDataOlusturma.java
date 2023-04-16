package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C13_Get_ExpectedDataOlusturma {
    @Test
    public void test01(){
        /*

https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunutest ediniz
Response body :
{
    "userId": 3,
"id": 22,
"title": "dolor sint quo a velit explicabo quia nam",
"body": "eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita earum mollitia molestiae aut atque rem suscipitnam impedit esse"
}*/

        //1- endpoint ve request body hazirla
        String url="https://jsonplaceholder.typicode.com/posts/22";

        //2-Expected data olustur

        JSONObject expectedDate=new JSONObject();
        expectedDate.put("userId",3);
        expectedDate.put("id",22);
        expectedDate.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDate.put("body","eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita earum mollitia molestiae aut atque rem suscipitnam impedit esse");
        System.out.println(expectedDate.toString());

        //3-request gonder ve kaydet et

        Response response=given().when().get(url);

        //4- Assertion
      /*Bugune kadar yaptigimiz yontemlede yapabiliriz
       response.then()
                .assertThat()
                .body("userId", equalTo(3),"id",equalTo(22),
                        "title",equalTo("dolor sint quo a velit explicabo quia nam"));

       */

        JsonPath responseJsonPath=response.jsonPath();
        Assert.assertEquals(expectedDate.get("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedDate.get("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expectedDate.get("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedDate.get("body"),responseJsonPath.getString("body"));

    }
}
