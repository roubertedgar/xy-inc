package com.xyinc.service;

import com.xyinc.model.Place;
import com.xyinc.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceTest {

    private static final String COORDINATES = "10,20";

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceService placeService;

    @Test
    public void shouldReturnAllPlaces() throws Exception {
        Place place = Place.builder().name("place1").build();
        doReturn(asList(place)).when(placeRepository).findAll();

        List<String> response = placeService.findPlaces();
        assertThat(response).isNotEmpty();
        assertThat(response).contains("place1");
    }

    @Test
    public void shouldReturnAllPlacesThatMatchCoordinates() throws Exception {
        Place place = Place.builder().name("place1").build();
        doReturn(asList(place)).when(placeRepository).findByCoordinate(10d, 20d, 10.0);

        List<String> response = placeService.findPlaces(COORDINATES);
        assertThat(response).isNotEmpty();
        assertThat(response).contains("place1");
    }

    @Test
    public void shouldSavePlaceAndReturnId() throws Exception {
        Place place = Place.builder().build();
        doReturn(Place.builder().id(1l).build()).when(placeRepository).save(place);

        Place ret = placeService.save(place);

        assertThat(ret).isNotNull();
        assertThat(ret.getId()).isEqualTo(1l);
    }
}