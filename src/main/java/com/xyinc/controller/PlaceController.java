package com.xyinc.controller;

import com.xyinc.model.Place;
import com.xyinc.service.PlaceService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPlaces() {
        final List<String> response = placeService.findPlaces();

        return toHttpResponse(response);
    }

    @RequestMapping(value = "/{coordinates}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPlaces(@PathVariable
                                                  @NotBlank
                                                  @Pattern(regexp = "\\d*,\\d*") String coordinates) {
        final List<String> response = placeService.findPlaces(coordinates);

        return toHttpResponse(response);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Place savePlace(@RequestBody Place place) {
        return placeService.save(place);
    }

    private ResponseEntity<List<String>> toHttpResponse(List<String> response) {
        final HttpStatus httpStatus = response.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(response, httpStatus);
    }
}
