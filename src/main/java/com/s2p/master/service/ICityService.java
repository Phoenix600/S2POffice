package com.s2p.master.service;

import com.s2p.master.dto.CityDto;
import com.s2p.master.model.City;

import java.util.List;
import java.util.UUID;

public interface ICityService {

    City createCity(City city);

    List<City> getAllCities();

    City getCityById(UUID cityId);

    City updateCity(UUID cityId, City cityDetails);

    void deleteCityById(UUID cityId);

    City getCityByName(String cityName);

    City updateCityByName(String cityName, City cityDetails);

    void deleteCityByName(String cityName);
}
