package com.s2p.master.service.impl;

import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.exception.BadRequestException;
import com.s2p.master.model.Country;
import com.s2p.master.repository.CountryRepository;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(AllureJunit5.class)
@Epic("Master Data Module")   // High-level grouping
@Feature("Country Service")   // Feature under testing
@Slf4j
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    private Country country1;
    private Country country2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        log.info("CountryServiceTest Setup Called");
        country1 = new Country();
        country1.setCountryId(UUID.randomUUID());
        country1.setCountry("India");

        country2 = new Country();
        country2.setCountryId(UUID.randomUUID());
        country2.setCountry("USA");
    }

    @Test
    @Story(value = "Create Country")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testCreateCountry_Success() {
        when(countryRepository.findAll()).thenReturn(Collections.singletonList(country1));
        when(countryRepository.save(any(Country.class))).thenReturn(country2);

        Country result = countryService.createCountry(country2);

        assertNotNull(result);
        assertEquals("USA", result.getCountry());
        verify(countryRepository, times(1)).save(country2);
    }

    @Test
    @Story(value = "Create Country With Duplicate")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testCreateCountry_AlreadyExists() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1));

        Country duplicate = new Country();
        duplicate.setCountry("India");

        assertThrows(BadRequestException.class, () -> countryService.createCountry(duplicate));
    }

    @Test
    @Story(value = "Get all Country Details")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testGetAllCountries() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));

        List<Country> countries = countryService.getAllCountries();

        assertEquals(2, countries.size());
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    @Story(value = "Get Country With ID")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testGetCountryById_Success() {
        when(countryRepository.findById(country1.getCountryId())).thenReturn(Optional.of(country1));

        Country result = countryService.getCountryById(country1.getCountryId());

        assertNotNull(result);
        assertEquals("India", result.getCountry());
    }

    @Test
    @Story(value = "Get Country With ID Not Found")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testGetCountryById_NotFound() {
        when(countryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> countryService.getCountryById(UUID.randomUUID()));
    }

    @Test
    @Story(value = "Update Country")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testUpdateCountry_Success() {
        when(countryRepository.findById(country1.getCountryId())).thenReturn(Optional.of(country1));
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));
        when(countryRepository.save(any(Country.class))).thenReturn(country1);

        Country updatedDetails = new Country();
        updatedDetails.setCountry("Bharat");

        Country result = countryService.updateCountry(country1.getCountryId(), updatedDetails);

        assertEquals("Bharat", result.getCountry());
        verify(countryRepository, times(1)).save(country1);
    }

    @Test
    @Story(value = "Update Country With Duplicate")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testUpdateCountry_DuplicateName() {
        when(countryRepository.findById(country1.getCountryId())).thenReturn(Optional.of(country1));
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));

        Country updatedDetails = new Country();
        updatedDetails.setCountry("USA");

        assertThrows(BadRequestException.class,
                () -> countryService.updateCountry(country1.getCountryId(), updatedDetails));
    }

    @Test
    @Story(value = "Delete Country ")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testDeleteCountry_Success() {
        when(countryRepository.findById(country1.getCountryId())).thenReturn(Optional.of(country1));

        countryService.deleteCountry(country1.getCountryId());

        verify(countryRepository, times(1)).delete(country1);
    }

    @Test
    @Story(value = "Get Country With Name")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testGetCountryByName_Success() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));

        Country result = countryService.getCountryByName("India");

        assertEquals("India", result.getCountry());
    }

    @Test
    @Story(value = "Get Country With Name Not Found")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testGetCountryByName_NotFound() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country2));

        assertThrows(ResourceNotFoundException.class,
                () -> countryService.getCountryByName("India"));
    }

    @Test
    @Story(value = "Update Country With Name")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testUpdateCountryByName_Success() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));
        when(countryRepository.save(any(Country.class))).thenReturn(country1);

        Country updatedDetails = new Country();
        updatedDetails.setCountry("Bharat");

        Country result = countryService.updateCountryByName("India", updatedDetails);

        assertEquals("Bharat", result.getCountry());
    }

    @Test
    @Story(value = "Deleting Country With ID")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testDeleteCountryById_Success() {
        when(countryRepository.findById(country1.getCountryId())).thenReturn(Optional.of(country1));

        countryService.deleteCountryById(country1.getCountryId());

        verify(countryRepository, times(1)).delete(country1);
    }

    @Test
    @Story(value = "Deleting Country With Name")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testDeleteCountryByName_Success() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));

        countryService.deleteCountryByName("India");

        verify(countryRepository, times(1)).delete(country1);
    }

    @Test
    @Story(value = "Deleting Country With Invalid Name")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    void testDeleteCountryByName_NotFound() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country2));

        assertThrows(ResourceNotFoundException.class,
                () -> countryService.deleteCountryByName("India"));
    }


    @Test
    @Story(value = "Create new country")    // Describes the user story
    @Description("Verify that a new country is created successfully when it does not already exist.")
    @Severity(SeverityLevel.CRITICAL) // Mark importance
    public void testCreateCountrySuccess() {
        // ✅ use class-level country1 and country2 from setUp()
        when(countryRepository.findAll()).thenReturn(Collections.singletonList(country1));
        when(countryRepository.save(any(Country.class))).thenReturn(country2);

        Country result = countryService.createCountry(country2);

        assertNotNull(result);
        assertEquals("USA", result.getCountry());
        verify(countryRepository, times(1)).save(country2);
    }
}
