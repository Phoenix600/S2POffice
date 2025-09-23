package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "DTO representing the response after a user registers")
public class RegisterResponseDto {

    @Schema(description = "Username of the registered user", example = "john_doe")
    private String username;

    @Schema(description = "Email of the registered user", example = "john@example.com")
    private String email;

    @Schema(description = "Roles of the registered user")
    private RolesDto rolesDto;

    @Schema(description = "JWT token issued for the registered user")
    private String token;
}
