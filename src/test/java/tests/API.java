package tests;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequest.NewContextOptions;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

import base.BaseFunction;

public class API extends BaseFunction{
    
    APIRequestContext request ;

    @Test
    public void meth(){

        //create headers
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("x-api-key","reqres-free-v1");

        NewContextOptions contextObj = new APIRequest.NewContextOptions()
            .setBaseURL("https://reqres.in")
            .setExtraHTTPHeaders(headers);

        request = playwright.request().newContext(contextObj);
        APIResponse response = request.get("/api/users/2");
        Assert.assertEquals(response.status(),200);
        System.out.println(response.text());


    }
    
}
