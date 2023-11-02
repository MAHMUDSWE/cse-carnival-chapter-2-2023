package com.reachout.backend.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CovidDeaths {
    @JsonProperty("new")
    private String newDeaths;

    @JsonProperty("1M_pop")
    private String deathsPerMillion;

    @JsonProperty("total")
    private int totalDeaths;

    // Getters and setters for the above fields
}
