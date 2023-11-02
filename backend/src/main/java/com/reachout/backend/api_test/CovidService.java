package com.reachout.backend.api_test;

;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reachout.backend.payload.dto.CovidCountryData;
import com.reachout.backend.payload.dto.CovidStatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@PropertySources({
        @PropertySource("classpath:application.properties"), // Your existing properties file
        @PropertySource("classpath:external-api.properties") // External properties file
})
public class CovidService {
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://covid-193.p.rapidapi.com/countries")
//            .get()
//            .addHeader("X-RapidAPI-Key", "f647841e56msh8db34d948ca8acbp19a45fjsnb2759b2331a3")
//            .addHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
//            .build();
//
//    Response response = client.newCall(request).execute();


    @Value("${rapidAPI.covid.data.url.countries}")
    private String urlCountry;

    @Value("${rapidAPI.covid.data.url.countries.stat}")
    private String urlCountryStat;

    @Value("${rapidAPI.covid.data.xRapidApiKey}")
    private String xRapidApiKey;
    @Value("${rapidAPI.covid.data.getxRapidApiHost}")
    private String getxRapidApiHost;

    @Autowired
    private RestTemplate restTemplate;

    public Object getAllCountryData() {
        try {
            //Header value is set
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", xRapidApiKey);
            headers.set("X-RapidAPI-Host", getxRapidApiHost);

            // Make a GET call to the RapidAPI

            ResponseEntity<JsonNode
                    > response = restTemplate.exchange(urlCountry,
                    HttpMethod.GET, new HttpEntity<>(headers), JsonNode.class);

            log.info("Output form rapidAPI:{}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            log.error("Something went wrong while getting value from RapidAPI", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint of RapidAPI for corona",
                    e
            );
        }
    }

    public Object getAllCountryCovidData() {
        try {
            //Header value is set
            HttpHeaders headers = gethttpHeaders();

            // Make a GET call to the RapidAPI

            // Make a GET call to the RapidAPI
            ResponseEntity<String> response = restTemplate.exchange(urlCountryStat,
                    HttpMethod.GET, new HttpEntity<>(headers), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            CovidStatResponse covidStatResponse = objectMapper.readValue(response.getBody(), CovidStatResponse.class);


            // Find the specific country data in the response
            List<String> countryNames = Arrays.asList("Afghanistan",
                    "Albania",
                    "Algeria",
                    "Andorra",
                    "Angola",
                    "Anguilla",
                    "Antigua-and-Barbuda",
                    "Argentina",
                    "Armenia",
                    "Aruba",
                    "Australia",
                    "Austria",
                    "Azerbaijan",
                    "Bahamas",
                    "Bahrain",
                    "Bangladesh",
                    "Barbados",
                    "Belarus",
                    "Belgium",
                    "Belize",
                    "Benin",
                    "Bermuda",
                    "Bhutan",
                    "Bolivia",
                    "Bosnia-and-Herzegovina",
                    "Botswana",
                    "Brazil",
                    "British-Virgin-Islands",
                    "Brunei",
                    "Bulgaria",
                    "Burkina-Faso",
                    "Burundi");
            List<CovidCountryData> dataCountry = new ArrayList<>();

            for (String countryName : countryNames) {
                for (CovidCountryData data : covidStatResponse.getResponse()) {
                    if (countryName.equals(data.getCountry())) {
                        dataCountry.add(data);
                        break;
                    }
                }
            }
            return dataCountry;
        } catch (Exception e) {
            log.error("Something went wrong while getting value from RapidAPI", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint of RapidAPI for corona", e);
        }
    }

    private HttpHeaders gethttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", xRapidApiKey);
        headers.set("X-RapidAPI-Host", getxRapidApiHost);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
