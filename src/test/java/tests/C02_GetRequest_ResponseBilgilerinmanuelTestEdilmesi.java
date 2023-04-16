package tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest_ResponseBilgilerinmanuelTestEdilmesi {

    @Test
    public  void test01() {

        //https://restful-booker.herokuapp.com/booking/10 url gidin bir Get request gonderdigimizde donen Response'u yazdirin

        //1- gerekli body ve and point hazirlama
        String url="https://restful-booker.herokuapp.com/booking/10";
        //2- Expected data hazirlama

        //3-Request gonderip, donen response'i kaydetme
        Response response = given().when().get(url);
        //System.out.println(response.getBody().toString()); bununla olmaz
        response.prettyPrint();
        System.out.println("status code : "+response.getStatusCode()+
                "\nContent type : " +response.getContentType()+
                "\nserver Header degeri : "+response.getHeader("Server")+
                "\nStatus Line : "+response.getStatusLine()+
                "\nResponse suresi : "+response.getTime()+"ms");

    }
}
