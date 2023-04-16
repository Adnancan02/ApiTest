package tests;

import baseUrl.BaseUrlHerokuAppi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C22_BaseUrlHerokuappQueryParam extends BaseUrlHerokuAppi {
    @Test
    public void test01() {
       //2- https://restful-booker.herokuapp.com/booking endpointine
        // gerekli Query parametrelerini yazarak “firstname” degeri “Jim”
        // ve “lastname” degeri “Jackson” olan rezervasyon oldugunu test edecek bir
        // GET request gonderdigimizde, donen response’un status code’unun 200 oldugunu ve
        // “Jim Jackson” ismine sahip en az bir booking oldugunu test edin

        //1-endpoint ve request olustur
        specHerokuapp.pathParam("pp1","booking")
                .queryParams("firstname","Susan","lastname","Jones");
        //2-expected data olustur

        //3-Request gonder ve donen response kaydet

        Response response=given().when().spec(specHerokuapp).get("/{pp1}");
        response.prettyPrint();
        //4-Assertion
        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.notNullValue());
    }
}
