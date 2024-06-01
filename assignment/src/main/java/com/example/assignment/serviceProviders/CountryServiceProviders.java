package com.example.assignment.serviceProviders;

import com.example.assignment.models.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author : Danushka Nanda Lochana
 * Description :  This interface represents the basic methods that
 *                we need to search county information
 */
public interface CountryServiceProviders {
    Flux<Country> getAllCountries();
    Mono<Country> getCountryByName(String name);
}