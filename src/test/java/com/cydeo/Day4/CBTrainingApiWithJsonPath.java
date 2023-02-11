package com.cydeo.Day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {
        //send a get request to student id 6 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Mike
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/student/all");
        assertEquals(200, response.statusCode());

        assertEquals("application/json;charset=UTF-8", response.header("content-type"));

//response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String firstName= jsonPath.getString("students.find {it.studentId==6}.firstName");
        System.out.println("firstName = " + firstName);
        assertEquals("Mike",firstName);


    }
}
