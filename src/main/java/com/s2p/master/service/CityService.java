package com.s2p.master.service;

import com.s2p.dto.masterDto.CityDto;
import com.s2p.master.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CityService implements ICityService
{
    @Autowired
    CityRepository cityRepository;

    @Override
    public CityDto createCity(CityDto cityDto) {
        return null;
    }

    @Override
    public CityDto getCityById(UUID cityId) {
        return null;
    }

    @Override
    public Set<CityDto> getAllCities() {
        return Set.of();
    }

    @Override
    public CityDto updateCityById(UUID cityId) {
        return null;
    }

    @Override
    public CityDto deleteCityById(UUID cityId) {
        return null;
    }
}
