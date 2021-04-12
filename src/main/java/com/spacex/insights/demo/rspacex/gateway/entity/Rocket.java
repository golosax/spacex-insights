package com.spacex.insights.demo.rspacex.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rocket {

    @JsonProperty("name")
    String name;

    @JsonProperty("height")
    Height height;

    @JsonProperty("mass")
    Mass mass;

    @JsonProperty("flickr_images")
    List<String> images;
}
