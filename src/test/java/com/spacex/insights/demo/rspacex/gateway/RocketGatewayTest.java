package com.spacex.insights.demo.rspacex.gateway;

import com.spacex.insights.demo.rspacex.gateway.entity.Height;
import com.spacex.insights.demo.rspacex.gateway.entity.Mass;
import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
class RocketGatewayTest {

    @Spy
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    @Autowired
    private RocketGateway rocketGateway;

    @BeforeEach
    public void init() {
        rocketGateway = spy(new RocketGateway("http://dummy.url", httpClient));
    }

    @Test
    void getRockets_success() throws IOException, InterruptedException {
        // given
        String rocketsJson = createRocketsJson();

        List<Rocket> expectedRocketList = new ArrayList<>() {{
            add(Rocket.builder()
                    .id("5e9d0d95eda69974db09d1ed")
                    .name("Falcon Heavy")
                    .height(Height.builder().heightInMeters(70.0).build())
                    .mass(Mass.builder().massInKg(1420788d).build())
                    .images(Arrays.asList("https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg",
                            "https://farm5.staticflickr.com/4645/38583830575_3f0f7215e6_b.jpg"))
                    .build());

            add(Rocket.builder()
                    .id("5e9d0d95eda69974db09d1ee")
                    .name("Falcon")
                    .build());
        }};

        // when
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(rocketsJson);

        // test
        Assertions.assertEquals(expectedRocketList, rocketGateway.getRockets());
    }

    @Test
    void getRockets_http404_returnEmptyList() throws IOException, InterruptedException {
        // when
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(404);

        // test
        Assertions.assertEquals(Collections.emptyList(), rocketGateway.getRockets());
    }

    @Test
    void getRockets_shouldReturnEmptyListIfExceptionHappens() throws IOException, InterruptedException {
        // when
        when(httpClient.send(any(), any())).thenThrow(InterruptedException.class);

        // test
        Assertions.assertEquals(Collections.emptyList(), rocketGateway.getRockets());
    }

    private String createRocketsJson() {
        return """
                [
                  {
                    "height": {
                      "meters": 70,
                      "feet": 229.6
                    },
                    "diameter": {
                      "meters": 12.2,
                      "feet": 39.9
                    },
                    "mass": {
                      "kg": 1420788,
                      "lb": 3125735
                    },
                    "flickr_images": [
                         "https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg",
                         "https://farm5.staticflickr.com/4645/38583830575_3f0f7215e6_b.jpg"
                       ],
                    "name": "Falcon Heavy",
                    "id": "5e9d0d95eda69974db09d1ed"
                    },
                    {
                     "name": "Falcon",
                     "id": "5e9d0d95eda69974db09d1ee"
                    }
                ]
                """;
    }
}