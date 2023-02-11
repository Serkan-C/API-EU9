package com.cydeo.Utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {

    @BeforeAll
    public static void init() {
        String baseUrl = "http://100.25.141.59:8000";
        baseURI = "http://100.25.141.59:8000";

        String dbUrl = "jdbc:oracle:thin:@100.25.141.59:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

      //  DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

    }
    @AfterAll
    public static void teardown(){

     //   DBUtils.destroy();
    }





}
