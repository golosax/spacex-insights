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

    public Object[] toArray() {
        return new Object[]{getName(),
                getHeight().getHeightInMeters(),
                getMass().getMassInKg(),
                getImages() == null ? "" : getImages().get(0)}; // taking only first url from images list in sake of pretty formatting
    }

}
