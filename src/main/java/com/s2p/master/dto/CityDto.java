package com.s2p.master.dto;

import com.s2p.master.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityDto {

    private UUID cityId;

    @NotBlank(message = "State name is mandatory")
    private String stateName;

    @NotBlank(message = "City name is mandatory")
    private String cityName; // Added for completeness



}
