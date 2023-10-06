package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class reqresInTests {
    /*
    1.Make request (POST) to https://reqres.in/api/login with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response  {"token": "QpwL5tke4Pnpja7X4"}

     3. check token is  QpwL5tke4Pnpja7X4
    */

    @Test
    void succesfulLoginTest(){
        String body = " {\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }
}
