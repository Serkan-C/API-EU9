package com.cydeo.Day2;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequest extends HRTestBase {
    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name



    @DisplayName("Get request to /regions")
    @Test
    public void test1(){
        Response response= get("/regions");
        System.out.println(response.statusCode());
    }
/*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @Test
    public void test2(){
        Response response=get("/regions/2");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Americas"));






    }


}
