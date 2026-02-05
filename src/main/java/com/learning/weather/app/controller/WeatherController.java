package com.learning.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/weather")
public class WeatherController {
    private final String location;

    @Autowired
    public WeatherController(@Value("${app.location}") final String location) {
        this.location = location;
    }

    @GetMapping(value = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test() {
        return "Hello world from %s!".formatted(this.location);
    }

    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public String app() {
        return "%s Weather app!".formatted(this.location);
    }
}
