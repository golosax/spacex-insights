package com.spacex.insights.demo.rspacex.service;

import com.spacex.insights.demo.rspacex.gateway.entity.Launch;

import java.util.List;

public interface LaunchesService {

    List<Launch> getPastLaunches();
}
