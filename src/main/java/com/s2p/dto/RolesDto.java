package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Role")
public class RolesDto {

    @Schema(description = "Unique ID of the Role", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID rolesId;

    @Schema(description = "Name of the Role", example = "ADMIN")
    private String rolesName;
}
