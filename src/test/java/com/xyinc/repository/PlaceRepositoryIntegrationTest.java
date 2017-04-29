package com.xyinc.repository;

import com.xyinc.model.Place;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Streams.stream;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceRepositoryIntegrationTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Before
    public void cleanData(){
        placeRepository.deleteAll();
    }

    @Test
    public void shouldSaveNewPlaceAndReturnGeneratedId() throws Exception {
        Place place = Place.builder().name("place1")
                .coordinateX(10d)
                .coordinateY(20d)
                .build();

        Place response = placeRepository.save(place);
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(place.getName());
        assertThat(response.getCoordinateX()).isEqualTo(place.getCoordinateX());
        assertThat(response.getCoordinateY()).isEqualTo(place.getCoordinateY());
    }

    @Test
    public void shouldReturnAllPlacesInTheDatabase() throws Exception {
        asList("place1", "place2", "place3").forEach(name -> createPlace(name, 10d, 20d));

        List<String> places = stream(placeRepository.findAll()).map(Place::getName).collect(toList());

        assertThat(places).isNotEmpty();
        assertThat(places).hasSize(3);
        assertThat(places).containsExactly("place1", "place2", "place3");
    }

    @Test
    public void shouldReturnPlaceThatMatchesTheGivenCoordinates() throws Exception {
        asList("place1", "place2", "place3").forEach(name -> createPlace(name, 10d, 20d));
        createPlace("place4", 30d, 50d);

        List<Place> places = placeRepository.findByCoordinate(30d, 40d, 10.00);

        assertThat(places).isNotEmpty();
        assertThat(places).hasSize(1);
        assertThat(places.get(0).getName()).isEqualTo("place4");
    }

    private Place createPlace(String name, Double coordinateX, Double coordinateY) {

        Place place = Place.builder().name(name)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .build();

        return placeRepository.save(place);
    }
}