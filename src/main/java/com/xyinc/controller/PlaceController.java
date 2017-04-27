package com.xyinc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @RequestMapping(method = RequestMethod.GET)
    public String getText(){
        return "testing";
    }
}
