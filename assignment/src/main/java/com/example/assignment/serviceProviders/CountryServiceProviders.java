package com.example.assignment;

import com.example.assignment.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryByName(String name);
}