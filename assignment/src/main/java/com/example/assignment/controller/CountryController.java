package com.example.assignment.controllers;

import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{name}")
    public Country getCountryByName(@PathVariable String name) {
        return countryService.getCountryByName(name);
    }
}