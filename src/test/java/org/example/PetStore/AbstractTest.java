package org.example.PetStore;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

abstract class AbstractTest {
    static Properties prop = new Properties();
    public static final String url ="https://petstore.swagger.io/v2/";
    static ResponseSpecification responseSpecification;
    @BeforeAll
    static void setUp () throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        FileInputStream fis;
        fis = new FileInputStream ("src/test/resources/my.properties");
        prop.load(fis);
        responseSpecification =new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
        RestAssured.responseSpecification = responseSpecification;

    }


}
