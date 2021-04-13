package com.spacex.insights.demo.rspacex.facade;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.gateway.entity.Height;
import com.spacex.insights.demo.rspacex.gateway.entity.Launch;
import com.spacex.insights.demo.rspacex.gateway.entity.Mass;
import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.LaunchesService;
import com.spacex.insights.demo.rspacex.service.RocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RocketsFacadeTest {

    @MockBean
    RocketService rocketService;

    @MockBean
    LaunchesService launchesService;

    RocketsFacade rocketsFacade;

    @BeforeEach
    public void setUp() {
        rocketsFacade = new RocketsFacade(rocketService, launchesService);
    }

    @Test
    void getRocketsData_shouldAggregateLaunchesAndRocketsData() {
        // given
        List<Launch> launches = createLaunches();
        List<Rocket> rockets = createRockets();
        List<RocketData> rocketDataList = createRocketData();

        // when
        when(rocketService.getRockets()).thenReturn(rockets);
        when(launchesService.getPastLaunches()).thenReturn(launches);

        // test
        assertEquals(rocketDataList, rocketsFacade.getRocketsData());
    }

    @Test
    void getRocketsData_rocketsAreEmpty_returnEmptyList() {
        // given
        List<Launch> launches = createLaunches();

        // when
        when(rocketService.getRockets()).thenReturn(Collections.emptyList());
        when(launchesService.getPastLaunches()).thenReturn(launches);

        // test
        assertEquals(Collections.emptyList(), rocketsFacade.getRocketsData());
    }

    @Test
    void getRocketsData_launchesAreEmpty_returnListOfRockets() {
        // given
        List<Rocket> rockets = createRockets();

        // when
        when(rocketService.getRockets()).thenReturn(rockets);
        when(launchesService.getPastLaunches()).thenReturn(Collections.emptyList());

        // test
        assertEquals(rocketsFacade.convertRocketsPayloadToRocketData(rockets), rocketsFacade.getRocketsData());

    }

    private ArrayList<RocketData> createRocketData() {
        return new ArrayList<RocketData>() {{
            add(RocketData.builder()
                    .id("id1")
                    .name("Falcon Heavy")
                    .height(70.0d)
                    .mass(1420788d)
                    .images(Arrays.asList("https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg",
                            "https://farm5.staticflickr.com/4645/38583830575_3f0f7215e6_b.jpg"))
                    .successfulLaunches(1)
                    .failedLaunches(0)
                    .build());

            add(RocketData.builder()
                    .id("id2")
                    .name("Falcon")
                    .successfulLaunches(0)
                    .failedLaunches(1)
                    .build());
        }};
    }

    private ArrayList<Rocket> createRockets() {
        return new ArrayList<>() {{
            add(Rocket.builder()
                    .id("id1")
                    .name("Falcon Heavy")
                    .height(Height.builder().heightInMeters(70.0).build())
                    .mass(Mass.builder().massInKg(1420788d).build())
                    .images(Arrays.asList("https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg",
                            "https://farm5.staticflickr.com/4645/38583830575_3f0f7215e6_b.jpg"))
                    .build());

            add(Rocket.builder()
                    .id("id2")
                    .name("Falcon")
                    .build());
        }};
    }

    private ArrayList<Launch> createLaunches() {
        return new ArrayList<>() {{
            add(Launch.builder()
                    .rocketId("id1")
                    .success(true)
                    .build());

            add(Launch.builder()
                    .rocketId("id2")
                    .success(false)
                    .build());

        }};
    }

}