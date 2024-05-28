package com.example.assignment.serviceProviders;

import com.example.assignment.models.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CountryServiceProviders {
    Flux<Country> getAllCountries();
    Mono<Country> getCountryByName(String name);
}