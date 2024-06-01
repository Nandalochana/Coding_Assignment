package com.example.assignment.models;

import lombok.Data;

/**
 * Author : Danushka Nanda Lochana
 * Description : This class working as the response wrapper and  is responsible for transferring object
 */
@Data
public class CountryResponse {
    private String name;
    private String countryCode;
    private String capital;
    private long population;
    private String flagFileUrl;

    /**
     * Constructor
     * @param name        : Country name
     * @param countryCode : Country code
     * @param capital     : Country capital
     * @param population  : Country population
     * @param flagFileUrl : Country flag
     */
    public CountryResponse(String name, String countryCode, String capital, long population, String flagFileUrl) {
        this.name = name;
        this.countryCode = countryCode;
        this.capital = capital;
        this.population = population;
        this.flagFileUrl = flagFileUrl;
    }
}
