package com.s2p.master.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Represents a source from which an entity originates or is referred.")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the source.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID sourceId;

    @Schema(description = "Name of the source.",
            example = "Online Advertisement")
    private String sourceName;
}
