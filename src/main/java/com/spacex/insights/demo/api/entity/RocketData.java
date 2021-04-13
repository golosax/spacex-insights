package com.spacex.insights.demo.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class RocketData {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("height")
    Double height;

    @JsonProperty("mass")
    Double mass;

    @JsonProperty("images")
    List<String> images;

    @JsonProperty("launches_successful")
    int successfulLaunches;

    @JsonProperty("failed_successful")
    int failedLaunches;

    public Object[] toArray() {
        return new Object[]{getName(),
                getHeight(),
                getMass(),
                getSuccessfulLaunches(),
                getFailedLaunches(),
                getImages() == null ? "" : getImages().get(0)}; // taking only first url from images list in sake of pretty formatting
    }
}
