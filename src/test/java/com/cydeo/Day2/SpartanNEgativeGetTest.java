package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanNEgativeGetTest {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.236.150.168:1000/ords/hr";

    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */
    @Test
    public void test1() {
        Response response=given().accept(ContentType.XML).when().get("/api/spartans/10");
        assertEquals(404,response.statusCode());
        assertEquals("text/html",response.contentType());


    }

}
