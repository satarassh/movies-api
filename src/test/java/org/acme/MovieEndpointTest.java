package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class MovieEndpointTest {

    // List all
    @Test
    public void testMoviesEndpoint() {
        given()
          .when().get("/movies")
          .then()
             .statusCode(200);
    }

    // Normal query
    @Test
    public void testMoviesEndpoint_GET_1() {
        given().contentType(ContentType.TEXT)
          .when().get("/movies/tt0993846")
          .then()
             .statusCode(200)
             .body("size()", is(6))
             .body("title", equalTo("The Wolf of Wall Street"));
    }

    // Query with non-existent imdbId
    @Test
    public void testMoviesEndpoint_GET_2() {
        given().contentType(ContentType.TEXT)
          .when().get("/movies/xxx")
          .then()
             .statusCode(404);
    }

    // Normal query
    @Test
    public void testMoviesEndpoint_PUT_1() {
        given().contentType(ContentType.JSON)
          .body("{\"title\":\"The Wolf of Ljubljana\"}")
          .when().put("/movies/tt0993846")
          .then()
             .statusCode(200);
    }

    // Query with non-existent imdbId
    @Test
    public void testMoviesEndpoint_PUT_2() {
        given().contentType(ContentType.JSON)
          .body("{\"title\":\"The Wolf of Ljubljana\"}")
          .when().put("/movies/xxx")
          .then()
             .statusCode(404);
    }

    // Normal query
    @Test
    public void testMoviesEndpoint_POST_1() {
        given().contentType(ContentType.JSON)
          .body("{\"imdbId\":\"tt0333701\",\"title\":\"Kajmak i marmelada\",\"genre\":\"Comedy\",\"year\":2003,\"description\":\"A story about a couple from the bottom of the social ladder.\"}")
          .when().post("/movies")
          .then()
             .statusCode(200);
    }

    // Query without unique imdbId
    @Test
    public void testMoviesEndpoint_POST_2() {
        given().contentType(ContentType.JSON)
          .body("{\"imdbId\":\"tt0993846\",\"title\":\"Kajmak i marmelada\",\"genre\":\"Drama\",\"year\":2003,\"description\":\"A story about a couple from the bottom of the social ladder.\"}")
          .when().post("/movies")
          .then()
             .statusCode(400);
    }

    

    // Normal query
    @Test
    public void testMoviesEndpoint_DELETE_1() {
        given().contentType(ContentType.TEXT)
          .when().delete("/movies/tt1320253")
          .then()
             .statusCode(200);
    }

    // Query with non-existent imdbId
    @Test
    public void testMoviesEndpoint_DELETE_2() {
        given().contentType(ContentType.TEXT)
          .when().delete("/movies/xxx")
          .then()
             .statusCode(404);
    }

}