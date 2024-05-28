package com.example.assignment.services;

import com.example.assignment.models.Country;
import com.example.assignment.serviceProviders.CountryServiceProviders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class CountryService implements CountryServiceProviders {


    @Value("${external.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Flux<Country> getAllCountries() {
        return Mono.fromCallable(() -> {
            String url = apiUrl + "/all";
            log.info("Fetch Url :" + url);
            Country[] countries = restTemplate.getForObject(url.trim(), Country[].class);
            log.info("Found Result :" + (countries != null ? countries.length : "0"));
            return countries != null ? Arrays.asList(countries) : new ArrayList<Country>();
        }).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Country> getCountryByName(String name) {
        String url = apiUrl + "/name/" + name;
        log.info("Fetch Url :" + url);
        return Mono.fromCallable(() -> {
            Country[] countries = restTemplate.getForObject(url.trim(), Country[].class);
            if (countries != null && countries.length > 0) {
                Country country = countries[0];
                log.info("Found Result :" + country.toString());
                return country;
            } else {
                log.info("No Result Found");
                return null;
            }
        }).flatMap(Mono::justOrEmpty);
    }
}