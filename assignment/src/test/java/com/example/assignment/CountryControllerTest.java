package com.example.assignment;

import com.example.assignment.controller.CountryController;
import com.example.assignment.models.Country;
import com.example.assignment.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    private Country country;

    public CountryControllerTest() {
    }

    @BeforeEach
    public void setup() {
        country = new Country();
        Country.Name name = new Country.Name();
        name.setCommon("Finland");
        country.setName(name);
        country.setCountryCode("FI");
        country.setCapital(new String[]{"Helsinki"});
        country.setPopulation(5491817);
        Country.Flags flags = new Country.Flags();
        flags.setSvg("https://flagcdn.com/fi.svg");
        country.setFlags(flags);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllCountries() throws Exception {
        Mockito.when(countryService.getAllCountries()).thenReturn(Collections.singletonList(country));
        mockMvc.perform(get("/countries/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Finland"))
                .andExpect(jsonPath("$[0].countryCode").value("FI"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getCountryByName_ReturnsCountryDetails() throws Exception {
        Mockito.when(countryService.getCountryByName(anyString())).thenReturn(country);
        mockMvc.perform(get("/countries/Finland")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Finland"))
                .andExpect(jsonPath("$.countryCode").value("FI"))
                .andExpect(jsonPath("$.capital").value("Helsinki"))
                .andExpect(jsonPath("$.population").value(5491817))
                .andExpect(jsonPath("$.flagFileUrl").value("https://flagcdn.com/fi.svg"));
    }
}