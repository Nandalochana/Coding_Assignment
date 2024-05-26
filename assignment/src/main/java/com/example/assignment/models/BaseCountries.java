package com.example.assignment.models;

import lombok.Data;

@Data
public class Country {
    private String name;
    private String countryCode;
    private String capital;
    private long population;
    private String flagFileUrl;
}
