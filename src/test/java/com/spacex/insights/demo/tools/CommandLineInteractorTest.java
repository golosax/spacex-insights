package com.spacex.insights.demo.tools;

import com.spacex.insights.demo.api.entity.RocketData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class CommandLineInteractorTest {

    CommandLineInteractor commandLineInteractor;

    @BeforeEach
    public void setUp() {
        commandLineInteractor = new CommandLineInteractor(null);
    }

    @Test
    void transformToDoubleArray_successFullyTransformed() {
        // given
        Object[][] rocketDataIn2DArray = new Object[][]{
                {"Falcon Heavy", 70.0d, null, 0, 0, ""},
                {"Falcon", null, null, 0, 0, ""}};

        // test
        Assertions.assertArrayEquals(rocketDataIn2DArray, commandLineInteractor.transformToDoubleArray(createRocketData()));
    }

    @Test
    void transformToDoubleArray_emptyEntry() {
        // test
        Assertions.assertArrayEquals(new Object[][]{}, commandLineInteractor.transformToDoubleArray(Collections.emptyList()));
    }

    @Test
    void transformToDoubleArray_nullEntry() {
        // test
        Assertions.assertArrayEquals(new Object[][]{}, commandLineInteractor.transformToDoubleArray(null));
    }


    private ArrayList<RocketData> createRocketData() {
        return new ArrayList<RocketData>() {{
            add(RocketData.builder()
                    .id("id1")
                    .name("Falcon Heavy")
                    .height(70.0d)
                    .build());

            add(RocketData.builder()
                    .id("id2")
                    .name("Falcon")
                    .build());
        }};
    }


}