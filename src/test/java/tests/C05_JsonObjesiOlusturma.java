package tests;

import org.json.JSONObject;
import org.junit.Test;

public class C05_JsonObjesiOlusturma {

    @Test
    public void test01(){
        /*
        {
"firstname":"Jim",
"additionalneeds":"Breakfast",
 "bookingdates":{
"checkin":"2018-01-01",
"checkout":"2019-01-01"
},
"totalprice":111,
"depositpaid":true,
"lastname":"Brown" }
         */

        JSONObject dateJsonObject=new JSONObject();
        //Once inner Json objesi olustururlur
        dateJsonObject.put("checkin","2018-01-01");
        dateJsonObject.put("checkout","2019-01-01");

        //Sonra auter json objesini olusturup, yeri geldiginde inner objeyi koyalim
        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname","Jim");
        requestBody.put("additionalneeds","Breakfast");
        requestBody.put("bookingdates",dateJsonObject);
        requestBody.put("totalprice",111);
        requestBody.put("depositpaid",true);
        requestBody.put("lastname","Brown");

        System.out.println(requestBody);
//{"firstname":"Jim",
// "additionalneeds":"Breakfast",
// "bookingdates":{
// "checkin":"2018-01-01",
// "checkout":"2019-01-01"},
// "totalprice":111,
// "depositpaid":true,
// "lastname":"Brown"}

    }
}
