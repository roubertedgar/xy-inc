package com.xyinc.service;

import com.xyinc.model.Place;
import com.xyinc.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceService {
    private static final int LONGITUDE_X = 0;
    private static final int LATITUDE_Y = 1;

    @Autowired
    private PlaceRepository placeRepository;

    public ResponseEntity<List<String>> findPlace() {
        return findPlace(null);
    }

    public ResponseEntity<List<String>> findPlace(String coordinates) {
        List<String> response = new ArrayList<>();

        if (coordinates == null) {
            placeRepository.findAll().forEach(p -> response.add(p.getName()));
        } else {
            String[] xy = coordinates.split(",");
            Double longitude = new Double(xy[LONGITUDE_X]);
            Double latitude = new Double(xy[LATITUDE_Y]);
           // placeRepository.findByLatitudeLongitude(longitude, latitude).forEach(p -> response.add(p.getName()));
        }

        HttpStatus httpStatus = response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
        return new ResponseEntity<>(response, httpStatus);
    }

    public Long save(Place place) {
        return placeRepository.save(place).getId();
    }
}
