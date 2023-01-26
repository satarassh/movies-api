package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class MovieEndpointTest {

    @Test
    public void testMoviesEndpoint() {
        given()
          .when().get("/movies")
          .then()
             .statusCode(200)
             .body("size()", is(2));
    }

    @Test
    public void testMoviesEndpoint_GET() {
        given().contentType(ContentType.JSON)
          .when().get("/movies/tt0993846")
          .then()
             .statusCode(200)
             .body("size()", is(6))
             .body("title", equalTo("The Wolf of Wall Street"));
    }

}