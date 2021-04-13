package com.spacex.insights.demo.rspacex.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rocket {

    @JsonProperty("id")
    String id;

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
