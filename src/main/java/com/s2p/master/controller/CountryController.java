package com.s2p.master.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;

import com.s2p.master.dto.CountryDto;
import com.s2p.master.model.Country;
import com.s2p.master.service.ICountryService;
import com.s2p.message.EApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final ICountryService countryService;

    // Create a new Country
    @PostMapping
    public ResponseEntity<ApiResponseDto<CountryDto>> createCountry(@Valid @RequestBody CountryDto countryDto) {
        Country country = new Country();
        country.setCountry(countryDto.getCountry());

        Country createdCountry = countryService.createCountry(country);

        CountryDto responseDto = new CountryDto(createdCountry.getCountryId(), createdCountry.getCountry());

        ApiResponseDto<CountryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_SAVED.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Get all Countries
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CountryDto>>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();

        List<CountryDto> countryDtos = new ArrayList<>();
        for (Country country : countries) {
            countryDtos.add(new CountryDto(country.getCountryId(), country.getCountry()));
        }

        ApiResponseDto<List<CountryDto>> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(countryDtos);

        return ResponseEntity.ok(response);
    }

    // Get Country by Name
    @GetMapping("/name/{countryName}")
    public ResponseEntity<ApiResponseDto<CountryDto>> getCountryByName(@PathVariable("countryName") String countryName) {
        Country country = countryService.getCountryByName(countryName);

        CountryDto responseDto = new CountryDto(country.getCountryId(), country.getCountry());

        ApiResponseDto<CountryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Update Country by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CountryDto>> updateCountry(@PathVariable("id") UUID id,
                                                                    @Valid @RequestBody CountryDto countryDto) {
        Country countryDetails = new Country();
        countryDetails.setCountry(countryDto.getCountry());

        Country updatedCountry = countryService.updateCountry(id, countryDetails);

        CountryDto responseDto = new CountryDto(updatedCountry.getCountryId(), updatedCountry.getCountry());

        ApiResponseDto<CountryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }


    // Update Country by Name
    @PutMapping("/name/{countryName}")
    public ResponseEntity<ApiResponseDto<CountryDto>> updateCountryByName(@PathVariable("countryName") String countryName,
                                                                          @Valid @RequestBody CountryDto countryDto) {
        Country countryDetails = new Country();
        countryDetails.setCountry(countryDto.getCountry());

        Country updatedCountry = countryService.updateCountryByName(countryName, countryDetails);

        CountryDto responseDto = new CountryDto(updatedCountry.getCountryId(), updatedCountry.getCountry());

        ApiResponseDto<CountryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_UPDATED.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    // Delete Country by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteCountryById(@PathVariable("id") UUID id) {
        countryService.deleteCountryById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Country deleted successfully");

        return ResponseEntity.ok(response);
    }

    // Delete Country by Name
    @DeleteMapping("/name/{countryName}")
    public ResponseEntity<ApiResponseDto<String>> deleteCountryByName(@PathVariable("countryName") String countryName) {
        countryService.deleteCountryByName(countryName);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Country deleted successfully");

        return ResponseEntity.ok(response);
    }



}
