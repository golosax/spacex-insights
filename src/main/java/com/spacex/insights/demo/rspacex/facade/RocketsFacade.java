package com.spacex.insights.demo.rspacex.facade;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.gateway.entity.Launch;
import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.LaunchesService;
import com.spacex.insights.demo.rspacex.service.RocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RocketsFacade {

    private final RocketService rocketService;
    private final LaunchesService launchesService;

    public List<RocketData> getRocketsData() {
        return gatherRocketsData();
    }

    private List<RocketData> gatherRocketsData() {
        List<Rocket> rockets = rocketService.getRockets();
        List<Launch> launches = launchesService.getPastLaunches();

        List<RocketData> rocketDataList = convertRocketsPayloadToRocketData(rockets);

        for (Launch launch : launches) {
            for (RocketData rocketData : rocketDataList) {
                if (launch.getRocketId().equals(rocketData.getId())) {
                    if (launch.isSuccess()) {
                        rocketData.setSuccessfulLaunches(rocketData.getSuccessfulLaunches() + 1);
                    } else {
                        rocketData.setFailedLaunches(rocketData.getFailedLaunches() + 1);
                    }
                }
            }
        }

        return rocketDataList;
    }

    List<RocketData> convertRocketsPayloadToRocketData(List<Rocket> rockets) {
        List<RocketData> rocketDataList = new ArrayList<>();

        for (Rocket rocket : rockets) {
            RocketData rocketData = RocketData.builder()
                    .id(rocket.getId())
                    .name(rocket.getName())
                    .height(rocket.getHeight() == null ? null : rocket.getHeight().getHeightInMeters())
                    .mass(rocket.getMass() == null ? null : rocket.getMass().getMassInKg())
                    .images(rocket.getImages())
                    .build();
            rocketDataList.add(rocketData);
        }

        return rocketDataList;

    }
}
