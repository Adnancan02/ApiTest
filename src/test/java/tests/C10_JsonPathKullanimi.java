package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {
    @Test
    public void method1(){

        JSONObject kisiBilgileri=new JSONObject();

        JSONObject adresJsonobj=new JSONObject();

        JSONArray telefonBilgileriArr=new JSONArray();

        JSONObject cepTelefonJsonObj=new JSONObject();
        JSONObject evTelJsonObj=new JSONObject();

        adresJsonobj.put("streetAddress","naist street");
        adresJsonobj.put("city","Nara");
        adresJsonobj.put("postalCode","630-0192");

        cepTelefonJsonObj.put("type","iphone");
        cepTelefonJsonObj.put("number","0123-4567-8910");
        evTelJsonObj.put("type","home");
        evTelJsonObj.put("number","0123-4567-8910");
        telefonBilgileriArr.put(cepTelefonJsonObj);
        telefonBilgileriArr.put(evTelJsonObj);
        kisiBilgileri.put("firstName","John");
        kisiBilgileri.put("lastName","Doe");
        kisiBilgileri.put("age",26);
        kisiBilgileri.put("address",adresJsonobj);
        kisiBilgileri.put("phoneNumbers",telefonBilgileriArr);

        System.out.println(kisiBilgileri);
        System.out.println("firstName : "+kisiBilgileri.get("firstName"));
        System.out.println("lastName : "+kisiBilgileri.get("lastName"));

        System.out.println("cadde : "+kisiBilgileri.getJSONObject("address").get("streetAddress"));
        System.out.println("city : "+kisiBilgileri.getJSONObject("address").get("city"));

        System.out.println("Cep tel no : "+kisiBilgileri.getJSONArray("phoneNumbers")
                .getJSONObject(0).get("number"));
        System.out.println("Cep tel no : "+kisiBilgileri.getJSONArray("phoneNumbers")
                .getJSONObject(0));


    }
}
