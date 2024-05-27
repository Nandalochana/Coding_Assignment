package com.example.assignment.services;


import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryServiceProviders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountryService implements CountryServiceProviders {

    private static final String API_URL = "https://restcountries.com/v3.1";
    private final RestTemplate restTemplate;

    public CountryService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Country> getAllCountries() {
        String fetchingUrl = API_URL + "/all";
        log.info("Fetch Country under url :"+fetchingUrl);
        Country[] countries = restTemplate.getForObject(fetchingUrl, Country[].class);
        if (countries != null) {
            log.info("Results Found");
            return Arrays.stream(countries)
                    .map(this::convertToCountry)
                    .collect(Collectors.toList());
        }
        else {
            log.info("Empty Results Found");
            return new ArrayList<>();
        }
    }

    @Override
    public Country getCountryByName(String name) {
        String fetchingUrl = API_URL + "/name/" + name;
        log.info("Fetch Country under url :"+fetchingUrl);

        String url = UriComponentsBuilder.fromHttpUrl(fetchingUrl)
                .queryParam("fullText", true)
                .toUriString();
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        if(countries!=null){
            log.info("Records Found/s : (Size: " + countries.length + ")");
            return countries.length > 0 ? convertToCountry(countries[0]) : null;
        }
        else {
            log.info("Empty Results Found");
        }
       return new Country();
    }

    private Country convertToCountry(Country country) {
        Country c = new Country();
        c.setName(country.getName());
        c.setCountryCode(country.getCountryCode());
        c.setCapital(country.getCapital());
        c.setPopulation(country.getPopulation());
        c.setFlags(country.getFlags());
        return c;
    }

    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(10000);
        //Read timeout
        clientHttpRequestFactory.setReadTimeout(10000);
        return clientHttpRequestFactory;
    }
}