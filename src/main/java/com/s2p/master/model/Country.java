package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Represents a country and its associated states.")
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the country.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID countryId;

    @Column(nullable = false, unique = true)
    @Schema(description = "Name of the country.",
            example = "India")
    private String country;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Schema(description = "Set of states that belong to this country.")
    private Set<State> states = new HashSet<>();
}
