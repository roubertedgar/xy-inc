package com.xyinc.controller;

import com.xyinc.model.Place;
import com.xyinc.repository.PlaceRepository;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceControllerFunctionalTest {

    @Autowired
    private PlaceRepository placeRepository;

    @LocalServerPort
    private int port;

    @Test
    public void shouldSavePlaceAndReturn200WithId() throws Exception {
        Place place = Place.builder().name("place").coordinateX(10.00).coordinateY(20.00).build();

        given()
            .port(this.port)
            .contentType("application/json")
            .body(place)
            .post("/place")
        .then()
            .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldReturnAllPlaces() throws Exception {
        List<String> placeNames = asList("place1", "place2", "place3");
        placeNames.forEach(name -> createPlace(name, 10d, 20d));

        given()
            .port(this.port)
            .get("/place")
        .then()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnPlaceInsideTheGivenCoordinates() throws Exception {
        List<String> placeNames = asList("place1", "place2", "place3");
        placeNames.forEach(name -> createPlace(name, 10d, 30d));

        given()
                .port(this.port)
                .get("/place/10,20")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnBadRequestForInvalidCordinates() throws Exception {
        List<String> placeNames = asList("place1", "place2", "place3");
        placeNames.forEach(name -> createPlace(name, 10d, 30d));

        given()
                .port(this.port)
                .get("/place/aa,20")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    private void createPlace(String name, Double coordinateX, Double coordinateY) {

        Place place = Place.builder().name(name)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .build();

         given()
            .port(this.port)
            .contentType("application/json")
            .body(place)
            .post("/place")
        .then()
            .statusCode(HttpStatus.SC_CREATED);
    }
}
