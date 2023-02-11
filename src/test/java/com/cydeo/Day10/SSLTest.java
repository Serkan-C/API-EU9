package com.cydeo.Day10;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SSLTest {

    @Test
    public void test1(){

        given().relaxedHTTPSValidation()
                .when().get("https://untrusted-root.badssl.com")
                .prettyPrint();

    }


}
