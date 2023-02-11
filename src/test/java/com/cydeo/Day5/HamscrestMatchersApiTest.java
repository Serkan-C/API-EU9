package com.cydeo.Day5;


import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HamscrestMatchersApiTest {

     /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){
        given().log().all().
                accept(ContentType.JSON)
                .and().pathParam("id",15)
        .when()
                .get("http://100.25.141.59:8000/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and().assertThat()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106))
                .log().all();

    }
}
