package com.s2p.master.repository;

import com.s2p.master.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    boolean existsByCountry(String countryName);
}
