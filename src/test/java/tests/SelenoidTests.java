package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;



public class SelenoidTests {
    /*
    1.Make request to https://selenoid.autotests.cloud/status
    2. Get response {"total":20,"used":0,"queued":0,"pending":0,"browsers":{
                        "android":{"8.1":{}},"chrome":{"100.0":{},"99.0":{}},
                        "chrome-mobile":{"86.0":{}},"firefox":{"97.0":{},"98.0":{}},"opera":{"84.0":{},"85.0":{}}}}
     3. check total 20
    */

    @Test
    void checkTotal(){
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));

    }
    @Test
    void checkWithGivenTotal(){
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));

    }
    @Test
    void checkWithStatusCodeTotal(){
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));

    }
    @Test
    void checkWithAlllogsTotal(){
        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));

    }
    @Test
    void checkWithSomelogsTotal(){
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));

    }
    @Test
    void checkChromeVersion(){
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));

    }

    @Test
    void checkJsonScheme(){
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"))
                .body(matchesJsonSchemaInClasspath("schemes/status-scheme-response.json"));

    }
}
