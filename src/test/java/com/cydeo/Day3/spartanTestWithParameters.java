package com.cydeo.Day3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class spartanTestWithParameters {

    @BeforeAll
    public static void init() {
        String baseUrl = "http://44.195.19.167:8000";
        baseURI = "http://44.195.19.167:8000";
        ;

    }

    /*   Given accept type is Json
         And Id parameter value is 5
         When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json
         And "Blythe" should be in response payload
      */
    @DisplayName("Get request to /api/spartans/{id} with ID 158 ")
    @Test
    public void test1() {
        Response response = given().
                accept(ContentType.JSON).
                and().pathParam("id", 158)
                .when()
                .get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("david"));

    }
/*
        TASK
        Given accept type is Json
        And Id parameter value is 50
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 50)
                .when().get("/api/spartans/{id}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));
        response.prettyPrint();

    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "je")
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("jessica"));
        response.prettyPrint();


    }

    @DisplayName("GET request to /api/spartans/search with Query Params (Map)")
    @Test
    public void test4() {
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("nameContains","je");
        queryMap.put("gender","Female");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("jessica"));
        response.prettyPrint();

}






    }
