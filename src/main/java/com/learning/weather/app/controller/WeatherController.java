package com.learning.weather.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/weather")
public class WeatherController {

    @GetMapping(value = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test() {
        return "Hello world!";
    }
}
