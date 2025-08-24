package com.s2p.master.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.master.dto.CityDto;
import com.s2p.master.model.City;
import com.s2p.master.service.ICityService;
import com.s2p.message.EApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final ICityService cityService;

    // Create City
    @PostMapping
    public ResponseEntity<ApiResponseDto<CityDto>> createCity(@Valid @RequestBody CityDto cityDto) {
        City city = new City();
        city.setCityName(cityDto.getCityName());

        City createdCity = cityService.createCity(city);

        CityDto responseDto = new CityDto(createdCity.getCityId(), null, createdCity.getCityName());

        ApiResponseDto<CityDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Get All Cities
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CityDto>>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        List<CityDto> cityDtos = new ArrayList<>();

        for (City city : cities) {
            cityDtos.add(new CityDto(city.getCityId(), null, city.getCityName()));
        }

        ApiResponseDto<List<CityDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(cityDtos);

        return ResponseEntity.ok(response);
    }

    // Get City by Name
    @GetMapping("/name/{cityName}")
    public ResponseEntity<ApiResponseDto<CityDto>> getCityByName(@PathVariable("cityName") String cityName) {
        City city = cityService.getCityByName(cityName);
        CityDto responseDto = new CityDto(city.getCityId(), null, city.getCityName());

        ApiResponseDto<CityDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Update City by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CityDto>> updateCity(@PathVariable("id") UUID id,
                                                              @Valid @RequestBody CityDto cityDto) {
        City cityDetails = new City();
        cityDetails.setCityName(cityDto.getCityName());

        City updatedCity = cityService.updateCity(id, cityDetails);

        CityDto responseDto = new CityDto(updatedCity.getCityId(), null, updatedCity.getCityName());

        ApiResponseDto<CityDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Delete City by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteCityById(@PathVariable("id") UUID id) {
        cityService.deleteCityById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("City deleted successfully");

        return ResponseEntity.ok(response);
    }

    // Delete City by Name
    @DeleteMapping("/name/{cityName}")
    public ResponseEntity<ApiResponseDto<String>> deleteCityByName(@PathVariable("cityName") String cityName) {
        cityService.deleteCityByName(cityName);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("City deleted successfully");

        return ResponseEntity.ok(response);
    }
}
