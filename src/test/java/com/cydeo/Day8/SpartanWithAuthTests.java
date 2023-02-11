package com.cydeo.Day8;

import com.cydeo.Utilities.*;
import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401 ")
    @Test
    public void test1() {
        given().accept(ContentType.JSON).
                when().
                get("/api/spartans")
                .then().statusCode(401)
                .log().all();


    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin() {
        //how to pass admin admin as a username and password ?
        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();

    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete() {
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
    }

    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */
    @DisplayName("Admin able to take all CRUD (create,read,update,delete)")
    @Test
    public void adminCRUD() {
        Spartan spartan1 = new Spartan();
        spartan1.setName("serkan");
        spartan1.setGender("Male");
        spartan1.setPhone(1234567891L);

        Spartan spartan2 = new Spartan();
        spartan2.setName("Ken");
        spartan2.setGender("Male");
        spartan2.setPhone(1234567899L);
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Bruce Wayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8711111111L);


//create a spartan
        System.out.println("create a spartan");
        Response response = given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(spartan1)
                .when().post("/api/spartans")
                .then().extract().response();
        int createdspartanID = response.path("data.id");
        System.out.println(createdspartanID);

//read a spartan
        System.out.println("read a spartan");

        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .and().param("id", createdspartanID)
                .when().get("/api/spartans")
                .then().statusCode(200);
        response.prettyPrint();
//update a spartan
        System.out.println("update a spartan");

        JsonPath jsonPath = given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .auth().basic("admin", "admin")
                .body(putRequestMap).log().body()
                .and().pathParam("id", createdspartanID)
                .when().put("/api/spartans/{id}")
                .then().extract().jsonPath();




//delete a spartan
        System.out.println("delete a spartan");
        given().pathParam("id", createdspartanID)
                .auth().basic("admin", "admin")
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();

//try to read deleted spartan
        System.out.println("read a spartan");

       given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .and().param("id",createdspartanID )
                .when().get("/api/spartans")
                .then().log().all();


    }


}
