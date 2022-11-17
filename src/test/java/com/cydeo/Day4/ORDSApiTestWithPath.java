package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

@DisplayName("GET request to countries with path method")
    @Test
    public void test1(){

    Response response= given().accept(ContentType.JSON)
            .queryParam("q","{\"region_id\":2}")
            .when()
            .get("/countries");

    assertEquals(200,response.statusCode());
    System.out.println("response.path(\"limit\") = " + response.path("limit"));
    System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

    String firstcountryId=response.path("items[0].country_id");
    System.out.println(firstcountryId);


    String secondcountryId=response.path("items[1].country_id");
    String secondcountryName=response.path("items[1].country_name");

    System.out.println(secondcountryId+" - "+secondcountryName);

    String thirdcountryHref=response.path("items[2].links[0].href");
    System.out.println(thirdcountryHref);

}

}
