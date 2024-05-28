package com.example.assignment.controller;

import com.example.assignment.models.Country;
import com.example.assignment.models.CountryResponse;
import com.example.assignment.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public Flux<CountryResponse> getAllCountries() {
        log.info("Fetching all countries");
        return countryService.getAllCountries()
                .map(this::convertToCountryResponse);
    }

    @GetMapping("/{name}")
    public Mono<CountryResponse> getCountryByName(@PathVariable String name) {
        log.info("Fetching country details under giving name :"+ name);
        return countryService.getCountryByName(name)
                .map(this::convertToCountryResponse);
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
}