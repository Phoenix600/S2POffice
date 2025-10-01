package com.s2p.master.model;

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
@Schema(description = "Represents a department and its associated colleges.")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the department.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID departmentId;

    @Column(unique = true, nullable = false)
    @Schema(description = "Name of the department.",
            example = "Computer Science")
    private String departmentName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Schema(description = "Set of colleges associated with this department.")
    private Set<College> college = new HashSet<>();
}
