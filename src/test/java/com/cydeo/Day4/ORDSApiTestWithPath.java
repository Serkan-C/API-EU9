package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstcountryId = response.path("items[0].country_id");
        System.out.println(firstcountryId);


        String secondcountryId = response.path("items[1].country_id");
        String secondcountryName = response.path("items[1].country_name");

        System.out.println(secondcountryId + " - " + secondcountryName);

        String thirdcountryHref = response.path("items[2].links[0].href");
        System.out.println(thirdcountryHref);

        List<String> countrNames = response.path("items.country_name");
        System.out.println("countrNames = " + countrNames);
List<Integer > allRegionsIDs=response.path("items.region_id");
        for (Integer eachRegionsID : allRegionsIDs) {
            System.out.println("eachRegionsID = " + eachRegionsID);
            assertEquals(2,eachRegionsID);


        }
        
        
    }
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":  \"IT_PROG\"}")
                .log().all()
                .when().get("/employees");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));
//        response.prettyPrint();
        List<String> allJobIDs=response.path("items.job_id");
        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);
        }


    }

}
