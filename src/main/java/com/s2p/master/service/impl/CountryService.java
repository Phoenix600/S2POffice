package com.s2p.master.service.impl;

import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.exception.BadRequestException;
import com.s2p.master.model.Country;
import com.s2p.master.repository.CountryRepository;
import com.s2p.master.service.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country createCountry(Country country) {
        List<Country> allCountries = countryRepository.findAll();
        for (Country existingCountry : allCountries) {
            if (existingCountry.getCountry().equalsIgnoreCase(country.getCountry())) {
                throw new BadRequestException("Country already exists: " + country.getCountry());
            }
        }
        return countryRepository.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(UUID countryId) {
        Country country = countryRepository.findById(countryId).orElse(null);
        if (country == null) {
            throw new ResourceNotFoundException("Country", "id", countryId.toString());
        }
        return country;
    }

    @Override
    public Country updateCountry(UUID countryId, Country countryDetails) {
        Country country = getCountryById(countryId);

        List<Country> allCountries = countryRepository.findAll();
        for (Country existingCountry : allCountries) {
            if (!existingCountry.getCountryId().equals(countryId) &&
                    existingCountry.getCountry().equalsIgnoreCase(countryDetails.getCountry())) {
                throw new BadRequestException("Country already exists: " + countryDetails.getCountry());
            }
        }

        country.setCountry(countryDetails.getCountry());
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(UUID countryId) {
        Country country = getCountryById(countryId);
        countryRepository.delete(country);
    }

    @Override
    public Country getCountryByName(String countryName) {
        List<Country> allCountries = countryRepository.findAll();
        for (Country country : allCountries) {
            if (country.getCountry().equalsIgnoreCase(countryName)) {
                return country;
            }
        }
        throw new ResourceNotFoundException("Country", "name", countryName);
    }

    @Override
    public Country updateCountryByName(String countryName, Country countryDetails) {
        List<Country> allCountries = countryRepository.findAll();
        Country existingCountry = null;

        for (Country country : allCountries) {
            if (country.getCountry().equalsIgnoreCase(countryName)) {
                existingCountry = country;
                break;
            }
        }

        if (existingCountry == null) {
            throw new ResourceNotFoundException("Country", "name", countryName);
        }

        existingCountry.setCountry(countryDetails.getCountry());
        return countryRepository.save(existingCountry);
    }


    @Override
    public void deleteCountryById(UUID id) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", id.toString()));
        countryRepository.delete(existingCountry);
    }

    @Override
    public void deleteCountryByName(String countryName) {
        List<Country> allCountries = countryRepository.findAll();
        Country existingCountry = null;

        for (Country country : allCountries) {
            if (country.getCountry().equalsIgnoreCase(countryName)) {
                existingCountry = country;
                break;
            }
        }

        if (existingCountry == null) {
            throw new ResourceNotFoundException("Country", "name", countryName);
        }

        countryRepository.delete(existingCountry);
    }


}
