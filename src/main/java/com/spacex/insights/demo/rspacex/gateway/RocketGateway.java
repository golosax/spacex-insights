package com.spacex.insights.demo.rspacex.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.RocketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class RocketGateway implements RocketService {

    @Value("${r-spacex.url}")
    private String url;

    private final HttpClient httpClient;

    public RocketGateway() {
        httpClient = HttpClient.newBuilder().build();
    }

    public static final String ROCKETS_PATH = "/v4/rockets";

    @Override
    @SneakyThrows
    public List<Rocket> getRockets() {
        List<Rocket> rocketList = Collections.emptyList();
        HttpRequest request = getHttpRequest(ROCKETS_PATH);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            Rocket[] rockets = objectMapper.readValue(response.body(), Rocket[].class);
            rocketList = Arrays.asList(rockets);
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
