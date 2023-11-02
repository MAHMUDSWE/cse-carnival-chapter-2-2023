package com.reachout.backend.api_test;

import java.util.List;

public class ApiResponseStats {
    private String get;
    private List<Object> parameters;
    private List<Object> errors;
    private int results;
    private List<CountryInfo> response;

    // Getters and setters go here

    // Nested class to represent the 'response' array elements
    public static class CountryInfo {
        private String continent;
        private String country;
        private int population;
        private CasesData cases;
        private DeathsData deaths;
        private TestsData tests;
        private String day;
        private String time;

        // Getters and setters for 'CountryInfo' fields go here

        // Nested classes for 'cases', 'deaths', and 'tests' objects
        public static class CasesData {
            private String newCases;
            private int active;
            private Integer critical;
            private int recovered;
            private String per1MPopulation;
            private int total;

            // Getters and setters for 'CasesData' fields go here
        }

        public static class DeathsData {
            private String newDeaths;
            private String per1MPopulation;
            private int total;

            // Getters and setters for 'DeathsData' fields go here
        }

        public static class TestsData {
            private String per1MPopulation;
            private int total;

            // Getters and setters for 'TestsData' fields go here
        }
    }
}

