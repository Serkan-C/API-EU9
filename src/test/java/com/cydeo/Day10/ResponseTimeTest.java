package com.cydeo.Day10;

import com.cydeo.Utilities.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {


    @Test
    public void test1(){

        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1500L)))
                .extract().response();

        System.out.println(response.getTime());


    }
}
