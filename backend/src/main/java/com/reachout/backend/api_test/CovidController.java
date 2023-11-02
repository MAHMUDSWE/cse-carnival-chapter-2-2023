package com.reachout.backend.api_test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
@RequiredArgsConstructor
public class CovidController {

    private final CovidService covidService;

    @GetMapping("/get-all-country")
    public ResponseEntity<?> callRapidEndpointToGetCountry(){
        return ResponseEntity.ok(covidService.getAllCountryData());
    }

    @GetMapping("/get-all-country-static")
    public ResponseEntity<?> callRapidEndpointToGetCovidData(){
        return ResponseEntity.ok(covidService.getAllCountryCovidData());
    }
}