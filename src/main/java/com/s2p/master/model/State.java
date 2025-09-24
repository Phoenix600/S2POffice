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
@Schema(description = "Represents a state and its associated cities.")
public class State extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the state.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID stateId;

    @Column(unique = true, nullable = false)
    @Schema(description = "Name of the state.",
            example = "Maharashtra")
    private String stateName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "state")
    @Schema(description = "Set of cities that belong to this state.")
    private Set<City> cities = new HashSet<>();
}
