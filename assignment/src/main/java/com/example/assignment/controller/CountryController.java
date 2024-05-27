package com.example.assignment.controller;

import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryServiceProviders;
import com.example.assignment.services.CountryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public List<CountryResponse> getAllCountries() {
        return countryService.getAllCountries().stream()
                .map(this::convertToCountryResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public CountryResponse getCountryByName(@PathVariable String name) {
        Country country = countryService.getCountryByName(name);
        return convertToCountryResponse(country);
    }

    private CountryResponse convertToCountryResponse(Country country) {
        CountryResponse response = new CountryResponse();
        response.setName(country.getName().getCommon());
        response.setCountryCode(country.getCountryCode());
        response.setCapital(country.getCapital() != null && country.getCapital().length > 0 ? country.getCapital()[0] : null);
        response.setPopulation(country.getPopulation());
        response.setFlagFileUrl(country.getFlags().getSvg());
        return response;
    }

    @Data
    public static class CountryResponse {
        private String name;
        private String countryCode;
        private String capital;
        private long population;
        private String flagFileUrl;
    }
}