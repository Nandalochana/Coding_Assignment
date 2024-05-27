package com.example.assignment.services;


import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryServiceProviders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.TypeReference;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountryService implements CountryServiceProviders {


    @Value("${external.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Country> getAllCountries() {
        String url = apiUrl + "/all";
        log.info("Fetch Country under url :" + url);
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        if (countries != null) {
            log.info("Results Found");
            return Arrays.asList(countries);
        }
        else {
            log.info("Empty Results Found");
            return new ArrayList<>();
        }
    }

    @Override
    public Country getCountryByName(String name) {
        String url = apiUrl + "/name/" + name;
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        if (countries != null && countries.length > 0) {
            log.info("Results Found");
            return countries[0];
        }
        else {
            log.info("Empty Results Found");
            return null;
        }
    }
}