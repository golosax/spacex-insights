package com.spacex.insights.demo.rspacex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ApplicationConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}