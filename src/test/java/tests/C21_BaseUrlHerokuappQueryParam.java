package tests;

import baseUrl.BaseUrlHerokuAppi;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C21_BaseUrlHerokuappQueryParam extends BaseUrlHerokuAppi {
    @Test
    public void test01(){
        //1- https://restful-booker.herokuapp.com/booking
        // endpointine gerekli Query parametrelerini yazarak
        // “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek
        // bir GET request gonderdigimizde, donen response’un status code’unun 200 oldugunu
        // ve “Eric” ismine sahip en az bir booking oldugunu test edin

        //1-Endpoint  ve request olustur
        specHerokuapp.pathParam("pp1","booking")
                .queryParam("firstname","Susan");

        //2-expected data olustur

        //3-request gonder ve donnen response kaydet
        Response response=given().when().spec(specHerokuapp).get("/{pp1}");
        response.prettyPrint();
        //4-Assertion
        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.hasSize(3));
    }
}
