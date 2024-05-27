package com.example.assignment.serviceProviders;

import com.example.assignment.models.Country;

import java.util.List;

public interface CountryServiceProviders {
    List<Country> getAllCountries();
    Country getCountryByName(String name);
}