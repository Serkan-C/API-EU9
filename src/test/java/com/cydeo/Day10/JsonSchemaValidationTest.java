package com.cydeo.Day10;

import com.cydeo.Utilities.*;
import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

            given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id",10)
                    .and()
                    .auth().basic("admin","admin")
            .when()
                    .get("/api/spartans/{id}")
            .then()
                    .statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                    .log().all();

    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema
    @DisplayName("Post request to all spartans and verify schema")
    @Test
    public void postSpartanSchemaTest(){
        Spartan spartan = new Spartan();
        spartan.setName("Serkan Spa");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);
        given()
                .accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .auth().basic("admin","admin").log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/Day10/spartanPostJsonSchema.json")));

    }




}
