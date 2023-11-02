package com.reachout.backend.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidStatResponse {
    String get;
    private List<CovidCountryData> response;

}
