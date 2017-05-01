package com.xyinc.controller;

import com.xyinc.model.Place;
import com.xyinc.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PlaceControllerTest {

    private static final String COORDINATES = "10,20";

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private PlaceController placeController;

    @Test
    public void shouldReturnAllPlacesFromService() throws Exception {
        List<String> places = asList("place");
        doReturn(places).when(placeService).findPlaces();

        ResponseEntity<List<String>> response = placeController.getPlaces();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(places);
    }

    @Test
    public void shouldReturnHttpNoContentWhenListIsEmpty() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(placeService).findPlaces();

        ResponseEntity<List<String>> response = placeController.getPlaces();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldGetPlacesFromGivenCordinates() throws Exception {
        List<String> places = asList("place");
        doReturn(places).when(placeService).findPlaces(COORDINATES);

        ResponseEntity<List<String>> response = placeController.getPlaces(COORDINATES);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(places);
    }

    @Test
    public void shouldReturnHttpNoContentWhenListIsEmptySearchingWithCoordinates() throws Exception {
        doReturn(Collections.EMPTY_LIST).when(placeService).findPlaces(COORDINATES);

        ResponseEntity<List<String>> response = placeController.getPlaces(COORDINATES);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void shouldSaveAndReturnPlace() throws Exception {
        Place place = Place.builder().id(1l).name("Lanchonete").coordinateX(20.0).coordinateY(10.0).build();
        doReturn(place).when(placeService).save(place);

        Place ret = placeController.savePlace(place);

        assertThat(ret).isNotNull();
        assertThat(ret.getId()).isEqualTo(1l);
        assertThat(ret.getName().equals("Lanchonete"));
        assertThat(ret.getCoordinateX().equals(20.0));
        assertThat(ret.getCoordinateY().equals(10.0));
    }
}