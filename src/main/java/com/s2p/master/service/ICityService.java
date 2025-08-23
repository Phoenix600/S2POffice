package com.s2p.master.service;

import com.s2p.master.dto.CityDto;

import java.util.Set;
import java.util.UUID;

public interface ICityService
{
    public abstract CityDto createCity(CityDto cityDto);

    public abstract CityDto getCityById(UUID cityId);

    public abstract Set<CityDto> getAllCities();

    public abstract CityDto updateCityById(UUID cityId);

    public abstract CityDto deleteCityById(UUID cityId);
}
