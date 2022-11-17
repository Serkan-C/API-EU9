package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {

    @BeforeAll
    public static void init() {
        String baseUrl = "http://44.195.19.167:8000";
        baseURI = "http://44.195.19.167:8000";

    }
}
