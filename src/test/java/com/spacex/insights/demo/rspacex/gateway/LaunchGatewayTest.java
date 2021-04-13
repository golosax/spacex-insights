package com.spacex.insights.demo.rspacex.gateway;

import com.spacex.insights.demo.rspacex.gateway.entity.Launch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
class LaunchGatewayTest {

    @Spy
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    @Autowired
    private LaunchGateway launchGateway;

    @BeforeEach
    public void init() {
        launchGateway = spy(new LaunchGateway("http://dummy-launch.url", httpClient));
    }

    @Test
    void getPastLaunches_success() throws IOException, InterruptedException {
        // given
        String launchesJson = createLaunchesJson();

        List<Launch> expectedLaunchesList = new ArrayList<>() {{
            add(Launch.builder()
                    .rocketId("5e9d0d95eda69973a809d1ed")
                    .success(true)
                    .build());

            add(Launch.builder()
                    .rocketId("5e9d0d95eda69973a809d1ec")
                    .success(false)
                    .build());

        }};

        // when
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(launchesJson);

        // test
        Assertions.assertEquals(expectedLaunchesList, launchGateway.getPastLaunches());
    }

    @Test
    void getPastLaunches_http404_returnEmptyList() throws IOException, InterruptedException {
        // when
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(404);

        // test
        Assertions.assertEquals(Collections.emptyList(), launchGateway.getPastLaunches());
    }

    @Test
    void getPastLaunches_shouldReturnEmptyListIfExceptionHappens() throws IOException, InterruptedException {
        // when
        when(httpClient.send(any(), any())).thenThrow(InterruptedException.class);

        // test
        Assertions.assertEquals(Collections.emptyList(), launchGateway.getPastLaunches());
    }

    private String createLaunchesJson() {
        return """
                [
                      {
                          "fairings": null,
                          "rocket": "5e9d0d95eda69973a809d1ed",
                          "success": true,
                          "failures": [],
                          "payloads": [
                              "5eb0e4d0b6c3bb0006eeb253"
                          ]
                      },
                       {
                          "rocket": "5e9d0d95eda69973a809d1ec",
                          "success": false,
                          "failures": [],
                          "payloads": [
                              "5eb0e4d0b6c3bb0006eeb153"
                          ]
                      }
                ]
                """;
    }

}