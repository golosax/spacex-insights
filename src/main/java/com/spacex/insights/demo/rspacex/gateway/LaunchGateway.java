package com.spacex.insights.demo.rspacex.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.insights.demo.rspacex.gateway.entity.Launch;
import com.spacex.insights.demo.rspacex.service.LaunchesService;
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
public class LaunchGateway implements LaunchesService {

    private String url;

    private final HttpClient httpClient;

    public LaunchGateway(@Value("${r-spacex.url}") String url, HttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
    }

    public static final String LAUNCHES_PATH = "/v4/launches/past";

    @Override
    public List<Launch> getPastLaunches() {
        List<Launch> launchesList = Collections.emptyList();
        HttpRequest request = getHttpRequest(LAUNCHES_PATH);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                Launch[] launches = objectMapper.readValue(response.body(), Launch[].class);
                launchesList = Arrays.asList(launches);
            }

        } catch (IOException | InterruptedException e) {
            log.error("Exception happened during fetching past launches", e);
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
