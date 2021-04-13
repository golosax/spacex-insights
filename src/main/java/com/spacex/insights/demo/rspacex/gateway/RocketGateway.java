package com.spacex.insights.demo.rspacex.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.RocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RocketGateway implements RocketService {

    private String url;
    private final HttpClient httpClient;

    public static final String ROCKETS_PATH = "/v4/rockets";

    public RocketGateway(@Value("${r-spacex.url}") String url, HttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
    }

    @Override
    public List<Rocket> getRockets() {
        List<Rocket> rocketList = Collections.emptyList();
        HttpRequest request = getHttpRequest(ROCKETS_PATH);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                Rocket[] rockets = objectMapper.readValue(response.body(), Rocket[].class);
                rocketList = Arrays.asList(rockets);
            }
        } catch (IOException | InterruptedException e) {
            log.error("Exception happened during fetching the rockets", e);
        }


        return rocketList;
    }

    private HttpRequest getHttpRequest(String apiPath) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + apiPath))
                .build();
        return request;
    }

}
