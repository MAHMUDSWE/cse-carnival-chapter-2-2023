package com.reachout.backend.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CovidCases {
    @JsonProperty("new")
    private String newCases;
    private int active;
    private int critical;
    private int recovered;

    @JsonProperty("1M_pop")
    private String casesPerMillion;

    private int total;
}
