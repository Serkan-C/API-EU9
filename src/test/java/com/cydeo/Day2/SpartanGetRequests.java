package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseUrl = "http://100.25.141.59:8000";


//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");


    }
/*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartans/156");

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");

        Assertions.assertTrue(response.body().asString().contains("John"));


    }

/*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("Get request to /api/hello")
    @Test
    public void test3() {
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));



        System.out.println("response.header(\"content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals("17", response.header("Content-Length"));
        Assertions.assertEquals("Hello from Sparta", response.body().asString());


    }
    @Test
    public void test4(){
        //send request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //verify we have headers named date
        //we use hasHeaderWithname method to verify header exist or not - it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        //how to get and header from response using header key ?
        //we use response.header(String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));
        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());
    }

}
