package com.s2p.master.repository;

import com.s2p.master.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    boolean existsByCountry(String countryName);
}
