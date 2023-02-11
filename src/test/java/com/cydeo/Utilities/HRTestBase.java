package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://100.25.141.59:1000/ords/hr";


    }
}
