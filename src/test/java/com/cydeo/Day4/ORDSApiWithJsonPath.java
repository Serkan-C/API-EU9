package com.cydeo.Day4;

import com.cydeo.Utilities.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        // response.prettyPrint();
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        List<String> countryNAmeWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNAmeWithRegionID2);
     /*
        for (String eachCountryId : allCountryIds) {
            if(jsonPath.getInt("items.region_id")==2){
                System.out.println("eachCountryId = " + eachCountryId);
            }
        }*/

    }

    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2() {
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();
       // response.prettyPrint();
        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> empNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(empNames);
        String kingFirstNAme= jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstNAme = " + kingFirstNAme);
        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);

    }
}