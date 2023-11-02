package com.reachout.backend.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class CovidTests {

    private String testsPerMillion;
    private int totalTests;

    @JsonProperty("1M_pop")
    public String getTestsPerMillion() {
        return testsPerMillion;
    }

    @JsonProperty("total")
    public int getTotalTests() {
        return totalTests;
    }


    @JsonSetter("1M_pop")
    public void setTestsPerMillion(String testsPerMillion) {
        this.testsPerMillion = testsPerMillion;
    }

    @JsonSetter("total")
    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }
}
