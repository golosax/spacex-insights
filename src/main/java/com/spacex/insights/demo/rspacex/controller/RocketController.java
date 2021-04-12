package com.spacex.insights.demo.rspacex.controller;

import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.RocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RocketController {

    private final RocketService rocketService;

    @GetMapping(value = "/rockets")
    public List<Rocket> start() {
        return rocketService.getRockets();
    }
}
