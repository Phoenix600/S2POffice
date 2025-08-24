package com.s2p.master.service;

import com.s2p.master.model.Country;

import java.util.List;
import java.util.UUID;

public interface ICountryService {

        // Create a new Country
        Country createCountry(Country country);

        // Get all Countries
        List<Country> getAllCountries();

        // Get Country by ID
        Country getCountryById(UUID countryId);

        // Update Country
        Country updateCountry(UUID countryId, Country countryDetails);

        // Delete Country
        void deleteCountry(UUID countryId);

        // Get Country by Name
        Country getCountryByName(String countryName);

        // Update Country by Name
        Country updateCountryByName(String countryName, Country countryDetails);

        // Delete Country By ID
        void deleteCountryById(UUID id);

        // Delete Country By Name
        void deleteCountryByName(String countryName);
}