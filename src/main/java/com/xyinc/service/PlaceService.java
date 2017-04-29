package com.xyinc.service;

import com.xyinc.model.Place;
import com.xyinc.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Streams.stream;
import static java.util.stream.Collectors.toList;

@Component
public class PlaceService {

    private static final int COORDINATE_X_INDEX = 0;
    private static final int COORDINATE_Y_INDEX = 1;
    private static final Double METERS_RANGE = 10.0;

    @Autowired
    private PlaceRepository placeRepository;

    public List<String> findPlaces() {
        return stream(placeRepository.findAll())
                .map(Place::getName)
                .collect(toList());
    }

    public List<String> findPlaces(String coordinates) {

        String[] xy = coordinates.split(",");
        Double coordinateX = new Double(xy[COORDINATE_X_INDEX]);
        Double coordinateY = new Double(xy[COORDINATE_Y_INDEX]);

        return placeRepository.findByCoordinate(coordinateX, coordinateY, METERS_RANGE).stream()
                .map(Place::getName)
                .collect(toList());
    }

    public Place save(Place place) {
        return placeRepository.save(place);
    }
}