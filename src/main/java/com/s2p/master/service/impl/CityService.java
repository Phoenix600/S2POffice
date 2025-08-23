package com.s2p.master.service.impl;

import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.exception.BadRequestException;
import com.s2p.master.model.City;
import com.s2p.master.repository.CityRepository;
import com.s2p.master.service.ICityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService implements ICityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(City city) {
        // Check for duplicate city name
        List<City> allCities = cityRepository.findAll();
        for (City existingCity : allCities) {
            if (existingCity.getCityName().equalsIgnoreCase(city.getCityName())) {
                throw new BadRequestException("City already exists: " + city.getCityName());
            }
        }
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(UUID cityId) {
        City city = cityRepository.findById(cityId).orElse(null);
        if (city == null) {
            throw new ResourceNotFoundException("City", "id", cityId.toString());
        }
        return city;
    }

    @Override
    public City updateCity(UUID cityId, City cityDetails) {
        City city = getCityById(cityId);

        // Check for duplicate city name
        List<City> allCities = cityRepository.findAll();
        for (City existingCity : allCities) {
            if (!existingCity.getCityId().equals(cityId) &&
                    existingCity.getCityName().equalsIgnoreCase(cityDetails.getCityName())) {
                throw new BadRequestException("City already exists: " + cityDetails.getCityName());
            }
        }

        city.setCityName(cityDetails.getCityName());
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityById(UUID cityId) {
        City city = getCityById(cityId);
        cityRepository.delete(city);
    }

    @Override
    public City getCityByName(String cityName) {
        List<City> allCities = cityRepository.findAll();
        for (City city : allCities) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                return city;
            }
        }
        throw new ResourceNotFoundException("City", "name", cityName);
    }

    @Override
    public City updateCityByName(String cityName, City cityDetails) {
        List<City> allCities = cityRepository.findAll();
        City existingCity = null;

        for (City city : allCities) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                existingCity = city;
                break;
            }
        }

        if (existingCity == null) {
            throw new ResourceNotFoundException("City", "name", cityName);
        }

        existingCity.setCityName(cityDetails.getCityName());
        return cityRepository.save(existingCity);
    }

    @Override
    public void deleteCityByName(String cityName) {
        List<City> allCities = cityRepository.findAll();
        City existingCity = null;

        for (City city : allCities) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                existingCity = city;
                break;
            }
        }

        if (existingCity == null) {
            throw new ResourceNotFoundException("City", "name", cityName);
        }

        cityRepository.delete(existingCity);
    }
}
