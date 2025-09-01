package com.s2p.master.service.impl;

import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.exception.BadRequestException;
import com.s2p.master.model.City;
import com.s2p.master.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city1;
    private City city2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        city1 = new City();
        city1.setCityId(UUID.randomUUID());
        city1.setCityName("Mumbai");

        city2 = new City();
        city2.setCityId(UUID.randomUUID());
        city2.setCityName("Delhi");
    }

    @Test
    void testCreateCity_Success() {
        when(cityRepository.findAll()).thenReturn(Collections.singletonList(city1));
        City newCity = new City();
        newCity.setCityName("Pune");

        when(cityRepository.save(newCity)).thenReturn(newCity);

        City result = cityService.createCity(newCity);
        assertEquals("Pune", result.getCityName());
    }

    @Test
    void testCreateCity_Duplicate_ThrowsException() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));
        City duplicate = new City();
        duplicate.setCityName("Mumbai");

        assertThrows(BadRequestException.class, () -> cityService.createCity(duplicate));
    }

    @Test
    void testGetAllCities() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        List<City> cities = cityService.getAllCities();

        assertEquals(2, cities.size());
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void testGetCityById_Found() {
        when(cityRepository.findById(city1.getCityId())).thenReturn(Optional.of(city1));

        City result = cityService.getCityById(city1.getCityId());

        assertEquals("Mumbai", result.getCityName());
    }

    @Test
    void testGetCityById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(cityRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cityService.getCityById(randomId));
    }

    @Test
    void testUpdateCity_Success() {
        when(cityRepository.findById(city1.getCityId())).thenReturn(Optional.of(city1));
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        City updatedCity = new City();
        updatedCity.setCityName("Hyderabad");

        when(cityRepository.save(city1)).thenReturn(city1);

        City result = cityService.updateCity(city1.getCityId(), updatedCity);

        assertEquals("Hyderabad", result.getCityName());
    }

    @Test
    void testUpdateCity_Duplicate_ThrowsException() {
        when(cityRepository.findById(city1.getCityId())).thenReturn(Optional.of(city1));
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        City updatedCity = new City();
        updatedCity.setCityName("Delhi");

        assertThrows(BadRequestException.class, () -> cityService.updateCity(city1.getCityId(), updatedCity));
    }

    @Test
    void testDeleteCityById() {
        when(cityRepository.findById(city1.getCityId())).thenReturn(Optional.of(city1));

        cityService.deleteCityById(city1.getCityId());

        verify(cityRepository, times(1)).delete(city1);
    }

    @Test
    void testGetCityByName_Found() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        City result = cityService.getCityByName("Delhi");

        assertEquals("Delhi", result.getCityName());
    }

    @Test
    void testGetCityByName_NotFound() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        assertThrows(ResourceNotFoundException.class, () -> cityService.getCityByName("Chennai"));
    }

    @Test
    void testUpdateCityByName_Success() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));
        when(cityRepository.save(city2)).thenReturn(city2);

        City updated = new City();
        updated.setCityName("New Delhi");

        City result = cityService.updateCityByName("Delhi", updated);

        assertEquals("New Delhi", result.getCityName());
    }

    @Test
    void testUpdateCityByName_NotFound() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        City updated = new City();
        updated.setCityName("Unknown");

        assertThrows(ResourceNotFoundException.class, () -> cityService.updateCityByName("Kolkata", updated));
    }

    @Test
    void testDeleteCityByName_Success() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        cityService.deleteCityByName("Mumbai");

        verify(cityRepository, times(1)).delete(city1);
    }

    @Test
    void testDeleteCityByName_NotFound() {
        when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        assertThrows(ResourceNotFoundException.class, () -> cityService.deleteCityByName("Kolkata"));
    }
}
