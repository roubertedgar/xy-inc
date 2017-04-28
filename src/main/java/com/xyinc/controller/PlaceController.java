package com.xyinc.controller;

import com.xyinc.model.Place;
import com.xyinc.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPlace() {
        return placeService.findPlace();
    }

    @RequestMapping(value = "/{coordinates}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPlace(@PathVariable String coordinates) {
        return placeService.findPlace(coordinates);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long savePlace(@RequestBody Place place) {
        return placeService.save(place);
    }
}
