package com.spacex.insights.demo.rspacex.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.insights.demo.rspacex.gateway.entity.Launch;
import com.spacex.insights.demo.rspacex.service.LaunchesService;
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
public class LaunchesGateway implements LaunchesService {

    @Value("${r-spacex.url}")
    private String url;

    private final HttpClient httpClient;

    public LaunchesGateway() {
        httpClient = HttpClient.newBuilder().build();
    }

    public static final String LAUNCHES_PATH = "/v4/launches/past";

    @SneakyThrows
    @Override
    public List<Launch> getPastLaunches() {
        List<Launch> launchesList = Collections.emptyList();
        HttpRequest request = getHttpRequest(LAUNCHES_PATH);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            Launch[] launches = objectMapper.readValue(response.body(), Launch[].class);
            launchesList = Arrays.asList(launches);
        }

        return launchesList;
    }

    private HttpRequest getHttpRequest(String apiPath) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + apiPath))
                .build();
        return request;
    }
}
