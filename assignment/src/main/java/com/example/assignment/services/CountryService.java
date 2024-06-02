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

    /**
     * Load all country list
     * @return Flux<Country>
     */
    @Override
    public Flux<Country> getAllCountries() {
        return Mono.fromCallable(() -> {
            Country[] countries = null;
            String url = apiUrl + "/all";
            try {
                log.info("Fetch Url :" + url);
                countries = getCountryInfo(url);
                log.info("Found Result :" + (countries != null ? countries.length : "0"));
            }
            catch (Exception exception) {
                log.info("Exception occurred :" + exception.getMessage());
                countries = getCountryInfo(url);
            }
            return countries != null ? Arrays.asList(countries) : new ArrayList<Country>();
        }).flatMapMany(Flux::fromIterable);
    }


    /***
     * Load Specific country information according to given name
     * @param name String (Country name)
     * @return Mono<Country>
     */
    @Override
    public Mono<Country> getCountryByName(String name) {
        return Mono.fromCallable(() -> {
            String url = apiUrl + "/name/" + name;
            log.info("Fetch Url :" + url);
            try {
                return getCountry(url);
            }
            catch (Exception exception) {
                log.info("Exception occurred :" + exception.getMessage());
                return getCountry(url);
            }
        }).flatMap(Mono::justOrEmpty);
    }

    /***
     * Load specific country information
     * Note: In this part it will validate the return information
     * @param url String (url)
     * @return Country
     */
    private Country getCountry(String url) {
        Country[]  countries = getCountryInfo(url);
        if (countries != null && countries.length > 0) {
            Country country = countries[0];
            log.info("Found Result :" + country.toString());
            return country;
        }
        else {
            log.info("No Result Found");
            return null;
        }
    }

    /***
     * In this part it will recursively call until the result found
     * Note: Sometime the country API is not working that why this kind of API is implemented
     * @param url String (url)
     * @return Country[]
     */
    private Country[] getCountryInfo(String url) {
        try {
            log.info("Loading Information : (URL : " + url + ")");
            return restTemplate.getForObject(url.trim(), Country[].class);
        }
        catch (Exception exception) {
            log.info("Exception occurred (Called Again):" + exception.getMessage());
            return getCountryInfo(url);
        }

    }
}