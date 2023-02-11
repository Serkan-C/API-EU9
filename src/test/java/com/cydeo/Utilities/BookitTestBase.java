package com.cydeo.Utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class BookitTestBase {
    public static RequestSpecification teacherReqSpec;
    public static RequestSpecification studentMemberReqSpec;
    public static RequestSpecification studentLeaderReqSpec;
    public static ResponseSpecification responseSpec;


    @BeforeAll
    public static void init(){
      baseURI ="https://cybertek-reservation-api-qa3.herokuapp.com";

        teacherReqSpec = given()
                                .accept(ContentType.JSON)
                                .header("Authorization",getTokenByRole("teacher"))
                                .log().all();
        studentMemberReqSpec =given()
                               .accept(ContentType.JSON)
                                .header("Authorization",getTokenByRole("student-member"))
                                .log().all();
        studentLeaderReqSpec =given()
                             .accept(ContentType.JSON)
                             .header("Authorization",getTokenByRole("student-leader"))
                             .log().all();
        responseSpec = expect()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .logDetail(LogDetail.ALL);
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

        //optional we can also make RS dynamic
    public static ResponseSpecification getDynamicResSpec(int statusCode) {

        return expect()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);

    }

    public static ResponseSpecification userCheck(String firstName,String lastName){

        return expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is(firstName))
                .body("lastName",is(lastName))
                .logDetail(LogDetail.ALL);

    }

    //teacher,student-member,student-leader
    public static RequestSpecification userReqSpec(String role){
        //advanced lazy way :)
       return given()
                .accept(ContentType.JSON)
                .header("Authorization",getTokenByRole(role))
                .log().all();

    }



    //teacher , student-member,student-leader
    //it will take user info from conf.properties
    public static String getTokenByRole(String role){
        //switch,if make sure you get correct user info
        //send request/get token/ return token
        String email, pass;

        switch (role) {

            case "teacher":
                email ="teachervawiltonamiss@gmail.com";
                pass = "wiltonamiss";
                break;
            case "student-member":
                email ="nshearsby7w@uiuc.edu";
                pass = "lorettabradnum";
                break;

            case "student-leader":
                email = "daldie7l@seattletimes.com";
                pass = "ruthannjohnes";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }

        String accessToken =
                given()
                .accept(ContentType.JSON)
                .queryParams("email",email,"password",pass)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        return "Bearer " + accessToken;



    }

}
