package tests;

import baseUrl.BaseUrlHerokuAppi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuapp extends BaseUrlHerokuAppi {

    @Test
    public void test01(){
        //
        //1- https://restful-booker.herokuapp.com/booking endpointine
        // bir GET request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin
        //1- endpoint ve request body olustur

        specHerokuapp.pathParam("pp1","booking");
        //2-Expected data olustur
        //3-request gonder ve donen response'i kaydet
        Response response=given().when().spec(specHerokuapp)
                .get("/{pp1}");
        //response.prettyPrint();
        //4-Assertion
        JsonPath responseJsonPath=response.jsonPath();
        int size=responseJsonPath.getList("bookingid").size();
        System.out.println(responseJsonPath.getList("bookingid").size());
        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.hasSize(size));

    }
}
