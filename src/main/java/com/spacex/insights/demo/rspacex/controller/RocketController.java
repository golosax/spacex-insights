package com.spacex.insights.demo.rspacex.controller;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.facade.RocketsFacade;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "This call returns rockets data",
            notes = "Steps done under this call:\n" +
                    "1) receives all rockets from https://api.spacexdata.com/v4/rockets\n" +
                    "2) receives all launches from https://api.spacexdata.com/v4/launches/\n" +
                    "3) returns rockets data aggregated with number of successfully done / failed launches.")
    @GetMapping(value = "/rockets")
    public List<RocketData> loadRocketsData() {
        return rocketsFacade.getRocketsData();
    }
}
