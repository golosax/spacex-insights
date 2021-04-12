package com.spacex.insights.demo.rspacex.controller;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.facade.RocketsFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RocketController {

    private final RocketsFacade rocketsFacade;

    @GetMapping(value = "/rockets")
    public List<RocketData> loadRocketsData() {
        return rocketsFacade.getRocketsData();
    }
}
