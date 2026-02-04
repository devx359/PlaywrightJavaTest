package tests;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequest.NewContextOptions;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import base.BaseFunction;

public class API extends BaseFunction{
    
    APIRequestContext request ;

  

    @Test
    public void get_request(){
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("x-api-key","reqres_6475b55f60e24eba9861d6ebd67a1e33");

        NewContextOptions contextObj = new APIRequest.NewContextOptions()
            .setBaseURL("https://reqres.in")
            .setExtraHTTPHeaders(headers);
        request = playwright.request().newContext(contextObj);

        APIResponse response = request.get("/api/users/2");
        Assert.assertEquals(response.status(),200);
        String responseText = response.text();
        String email = JsonPath.read(responseText,"$.data.email");
        String firstName = JsonPath.read(responseText,"$.data.first_name");
        System.out.println(email+"  "+firstName);
    }   

    @Test
    public void post_request(){
        System.out.println("****** POST *****");
        //headers
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("x-api-key","reqres_6475b55f60e24eba9861d6ebd67a1e33");
        //request context
        NewContextOptions contextop = new APIRequest.NewContextOptions()
            .setBaseURL("https://reqres.in")
            .setExtraHTTPHeaders(headers);
        request = playwright.request().newContext(contextop);

        //payload json datas
        HashMap<String,String> payload = new HashMap<>();
        payload.put("email", "eve.holt@reqres.in");
        payload.put("password", "pistol");

        //RequestOptions
        RequestOptions reqoptions = RequestOptions.create().setData(payload);

        APIResponse resp = request.post("/api/register", reqoptions);
        String respText = resp.text();
        // System.out.println(respText);
        String token =JsonPath.read(respText,"$.token");
        System.out.println("###Extracted token is "+token);
    }
    
}
