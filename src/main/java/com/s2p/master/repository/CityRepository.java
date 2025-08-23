package com.s2p.master.repository;

import com.s2p.master.model.City;
import com.s2p.master.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findByState(State state);
    boolean existsByCityNameAndState(String cityName, State state);
}
