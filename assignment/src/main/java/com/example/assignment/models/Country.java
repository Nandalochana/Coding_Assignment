package com.example.assignment.models;

import lombok.Data;

@Data
public class Country {
    private Name name;
    private String countryCode;
    private String[] capital;
    private long population;
    private Flags flags;

    @Data
    public static class Name {
        private String common;
        private String official;
    }

    @Data
    public static class Flags {
        private String png;
        private String svg;
    }
}