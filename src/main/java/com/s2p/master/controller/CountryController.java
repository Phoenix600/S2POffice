package com.s2p.master.controller;

import com.s2p.constants.EOperationStatus;
import com.s2p.dto.ApiResponseDto;
import com.s2p.master.dto.CountryDto;
import com.s2p.master.model.Country;
import com.s2p.master.service.ICountryService;
import com.s2p.message.EApiResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Country Management", description = "Operations related to managing countries")
public class CountryController {

    private final ICountryService countryService;

    @PostMapping
    @Operation(summary = "Create a new country", description = "Adds a new country record to the system")
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

    @GetMapping
    @Operation(summary = "Get all countries", description = "Fetch a list of all countries")
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

    @GetMapping("/name/{countryName}")
    @Operation(summary = "Get country by name", description = "Retrieve a country using its name")
    public ResponseEntity<ApiResponseDto<CountryDto>> getCountryByName(@PathVariable String countryName) {
        Country country = countryService.getCountryByName(countryName);
        CountryDto responseDto = new CountryDto(country.getCountryId(), country.getCountry());

        ApiResponseDto<CountryDto> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_FOUND.getMessage());
        response.setData(responseDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update country by ID", description = "Update an existing country using its unique ID")
    public ResponseEntity<ApiResponseDto<CountryDto>> updateCountry(@PathVariable UUID id,
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

    @PutMapping("/name/{countryName}")
    @Operation(summary = "Update country by name", description = "Update an existing country using its name")
    public ResponseEntity<ApiResponseDto<CountryDto>> updateCountryByName(@PathVariable String countryName,
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete country by ID", description = "Delete an existing country using its unique ID")
    public ResponseEntity<ApiResponseDto<String>> deleteCountryById(@PathVariable UUID id) {
        countryService.deleteCountryById(id);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Country deleted successfully");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/name/{countryName}")
    @Operation(summary = "Delete country by name", description = "Delete an existing country using its name")
    public ResponseEntity<ApiResponseDto<String>> deleteCountryByName(@PathVariable String countryName) {
        countryService.deleteCountryByName(countryName);

        ApiResponseDto<String> response = new ApiResponseDto<>();
        response.setStatus(EOperationStatus.RESULT_SUCCESS);
        response.setMessage(EApiResponseMessage.DATA_DELETED.getMessage());
        response.setData("Country deleted successfully");

        return ResponseEntity.ok(response);
    }
}
