package com.example.assignment.services;


import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryServiceProviders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryServiceProviders {

    private static final String API_URL = "https://restcountries.com/v3.1/all";
    private final RestTemplate restTemplate;

    public CountryServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Country> getAllCountries() {
        Country[] countries = restTemplate.getForObject(API_URL, Country[].class);
        return Arrays.stream(countries)
                .map(this::convertToCountry)
                .collect(Collectors.toList());
    }

    @Override
    public Country getCountryByName(String name) {
        String url = UriComponentsBuilder.fromHttpUrl("https://restcountries.com/v3.1/name/" + name)
                .queryParam("fullText", true)
                .toUriString();
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        return countries != null && countries.length > 0 ? convertToCountry(countries[0]) : null;
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
}