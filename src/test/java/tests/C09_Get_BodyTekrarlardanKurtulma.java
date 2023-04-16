package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C09_Get_BodyTekrarlardanKurtulma {
    @Test
    public void test01(){

        /*
          https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
   status code’unun 200,
   ve content type’inin application-json, ve response body’sindeki
   "firstname“in, "Susan",
    ve "lastname“in, "Jackson",
    ve "totalprice“in, 1000 den kucuk oldugunu,
    ve "depositpaid“in, false,
    ve "additionalneeds“in, bos birakilmadigini
     oldugunu test edin
         */

        //1-end point ve request body hazirla
        String url="https://restful-booker.herokuapp.com/booking/10";

        //2- expected data olustur

        //3-request gonderip donen response kaydet
        Response response=given().when().get(url);

        response.prettyPrint();
        //4-Assertion
       /* ikinci yontemle ile yaptigimiz bu tetsimizi degistirmasin diye yorum yapildi
       response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Susan"))
                .body("lastname", Matchers.equalTo("Smith"))
                .body("totalprice",Matchers.lessThan(1000))
                .body("depositpaid", Matchers.equalTo(true))
                .body("additionalneeds",Matchers.notNullValue());

        */
response.then().assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("firstname", equalTo("Eric"),
                "lastname", equalTo("Jones"),"totalprice",lessThan(1000),
                "depositpaid",equalTo(false));
// "additionalneeds",notNullValue()

    }
}
