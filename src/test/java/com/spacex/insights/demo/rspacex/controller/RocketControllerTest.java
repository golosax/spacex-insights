package com.spacex.insights.demo.rspacex.controller;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.facade.RocketsFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RocketController.class)
@ExtendWith(SpringExtension.class)
class RocketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RocketController rocketController;

    @MockBean
    private RocketsFacade rocketsFacade;

    @Test
    void loadRocketsData_checkRocketsDataMapping() throws Exception {
        // when
        when(rocketsFacade.getRocketsData()).thenReturn(Collections.emptyList());
//        when(rocketController.loadRocketsData()).thenReturn(Collections.emptyList());

        // test
        mockMvc.perform(get("/rockets"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(rocketsFacade, times(1)).getRocketsData();
    }

    @Test
    void loadRocketsData_checkRocketsDataReturned() throws Exception {
        // given
        rocketController = new RocketController(rocketsFacade);

        // when
        when(rocketsFacade.getRocketsData()).thenReturn(createRocketData());

        //test
        assertEquals(createRocketData(), rocketController.loadRocketsData());
        verify(rocketsFacade, times(1)).getRocketsData();
    }

    private ArrayList<RocketData> createRocketData() {
        return new ArrayList<RocketData>() {{
            add(RocketData.builder()
                    .id("id1")
                    .name("Falcon Heavy")
                    .build());

            add(RocketData.builder()
                    .id("id2")
                    .name("Falcon")
                    .successfulLaunches(10)
                    .build());
        }};
    }
}