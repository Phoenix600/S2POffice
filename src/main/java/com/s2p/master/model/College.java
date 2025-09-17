package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Represents a college entity with its departments and location details.")
public class College extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the college.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID collegeId;

    @Column(nullable = false, unique = true)
    @Schema(description = "Name of the college.",
            example = "National Institute of Technology")
    private String collegeName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "college_department",
            joinColumns = { @JoinColumn(name = "college_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") })
    @Schema(description = "Set of departments associated with this college.")
    private Set<Department> departmentSet = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id", referencedColumnName = "countryId")
    @Schema(description = "Country where the college is located.")
    private Country country;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "state_id", referencedColumnName = "stateId")
    @Schema(description = "State where the college is located.")
    private State state;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id", referencedColumnName = "cityId")
    @Schema(description = "City where the college is located.")
    private City city;
}
