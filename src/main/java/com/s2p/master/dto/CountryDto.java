package com.s2p.master.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryDto {

    private UUID countryId;

    @NotBlank(message = "Country name is mandatory")
    private String country;
}
