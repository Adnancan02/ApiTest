package tests;

import baseUrl.BaseUrlDummyExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C33_Get_Pojo extends BaseUrlDummyExample {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
Response Body
{
"status": "success",
"data": {
"id": 3,
"employee_name": "Ashton Cox",
"employee_salary": 86000,
"employee_age": 66,
"profile_image": ""
},
"message": "Successfully! Record has been fetched."
 }   */

    @Test
    public void test01(){
        //1- Request url ve body olustur
        specDummyExample.pathParams("pp1","employee","pp2",3);
        // 2- soruda varsa expected data olustur
        PojoDummyExampleData dataPojo=
                new PojoDummyExampleData(3,"Ashton Cox",86000,66,"");

        PojoDummyExampleResponse expectedResponseBody=
                new PojoDummyExampleResponse("success",dataPojo,"Successfully! Record has been fetched.");
        //3-Response olustur, request gonderip sonucu response'a ata
        Response response=given().spec(specDummyExample)
                .when()
                .get("{pp1}/{pp2}");
        //Assertion
        /*
        pojos.PojoDummyExampleResponse@46678e49
        [status=success,data=
        pojos.PojoDummyExampleData@748e9b20
        [id=3,
        employeeName=Ashton Cox,
        employeeSalary=86000,
        employeeAge=66,
        profileImage=],
        message=Successfully! Record has been fetched.]*/
        //expectedResponseBody (Pojo) <===>Response --->eger Pojo lar otomatik olarak olusturulursa
        PojoDummyExampleResponse responsePojo=response.as(PojoDummyExampleResponse.class);
        // isim ler de problem olacak bu yuzden response pjo cerilmez bunun yerine "ResponseJsonPath" cevrilir
        JsonPath responseJP=response.jsonPath();
        assertEquals(expectedResponseBody.getStatus(),responseJP.getString("status"));
        assertEquals(expectedResponseBody.getMessage(),responseJP.getString("message"));
        assertEquals(expectedResponseBody.getData().getId(),responseJP.get("data.id"));
        assertEquals(expectedResponseBody.getData().getEmployeeName(),responseJP.get("data.employee_name"));
        assertEquals(expectedResponseBody.getData().getEmployeeSalary(),responseJP.get("data.employee_salary"));
        assertEquals(expectedResponseBody.getData().getEmployeeAge(),responseJP.get("data.employee_age"));
        assertEquals(expectedResponseBody.getData().getProfileImage(),responseJP.get("data.profile_image"));
    }
}
