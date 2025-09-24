package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
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
@Schema(description = "Represents a city and its associated state.")
public class City extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the city.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID cityId;

    @Schema(description = "Name of the city.",
            example = "Mumbai")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "stateId")

    @Schema(description = "The state this city belongs to.")
    private State state;
}
