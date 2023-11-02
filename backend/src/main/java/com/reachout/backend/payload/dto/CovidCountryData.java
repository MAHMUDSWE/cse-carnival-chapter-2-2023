package com.reachout.backend.payload.dto;

import lombok.Data;

@Data
public class CovidCountryData {
    private String continent;
    private String country;
    private int population;
    private CovidCases cases;
    private CovidDeaths deaths;
    private CovidTests tests;
    private String day;
    private String time;
}
