package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.pojosHavaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C34_Post_Pojo {

    @Test
    public void test01(){
        /*

https://api.openweathermap.org/data/2.5/weather?q=London&a ppid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0 url’ine bir
post request gonderdigimizde donen response’un asagidaki body’ye sahip oldugunu test ediniz

         */

        //1- Request url ve body olustur
        String url="https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0";

        //2- soruda varsa expected pojoDummyExampleData olustur
        Coord coordPojo=new Coord(-0.1257f,51.5085f);
        List<Weather> weatherList=new ArrayList<>();
        Weather weatherPojo=new Weather(804,"Clouds","overcast clouds","04d");
        weatherList.add(weatherPojo);
        Main mainPojo=new Main(283.5f,282.42f,281.51f,285.01f,1026,70);
        Wind windPojo=new Wind(6.17f,70);
        Clouds cloudsPojo=new Clouds(100);
        Sys sysPojo=new Sys(2,2075535,"GB",1681793941,1681844425);
       PojoHavaDurumu expectedResponseBody=
               new PojoHavaDurumu(coordPojo,weatherList,"stations",mainPojo,10000,
                       windPojo,cloudsPojo,1681827620,sysPojo,3600,2643743,"London",200);
       // System.out.println(expectedResponseBody);
        //3-Response olustur, request gonderip sonucu response'a ata
        Response response=given().when().get(url);
        response.prettyPrint();
        //4-Assertion
        //expectedResponseBody pojo <===> response
        PojoHavaDurumu responsePojo= response.as(PojoHavaDurumu.class);
        //response'i Pojo'ya cevirdigimizde tum bilgileri getirirse responsePojo'yu assertion'da kullanabilirz
        //Eger null deger donerse response JsonPath yapip assertion'da kullanabilirz
        System.out.println(responsePojo);

        JsonPath responseJP=response.jsonPath();
        //expectedResponseBody pojo <===> responseJP

        assertEquals(expectedResponseBody.getCoord().getLon(),responseJP.get("coord.lon"));
        assertEquals(expectedResponseBody.getCoord().getLat(),responseJP.get("coord.lat"));
        assertEquals(expectedResponseBody.getBase(),responseJP.get("base"));
        assertEquals(expectedResponseBody.getSys().getCountry(),responseJP.get("sys.country"));
        assertEquals(expectedResponseBody.getName(),responseJP.get("name"));
    }
}
