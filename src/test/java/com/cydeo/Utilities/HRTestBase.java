package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://54.236.150.168:1000/ords/hr";


    }
}
