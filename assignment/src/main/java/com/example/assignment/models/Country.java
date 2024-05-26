package com.example.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class BaseCountries {
    private Name name;
    private String cca2;
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