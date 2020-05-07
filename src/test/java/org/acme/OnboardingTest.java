package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OnboardingTest {

    @Test
    void testAdultOnboarding() {
        given()
          .when()
            .body("{\"person\":{\"age\":18,\"name\":\"Adult Person\"}}")
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/onboarding")
          .then()
            .statusCode(200)
            .body("person.name", is("Adult Person"));
    }

    @Test
    void testKidHasIncreasedAge() {
        given()
          .when()
            .body("{\"person\":{\"age\":7,\"name\":\"Kid Kidson\"}}")
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/onboarding")
          .then()
            .statusCode(200)
            .body("person.age", is(8));
    }
    
}