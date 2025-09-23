package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for creating or updating Users")
public class UsersDto {
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    @Email
    private String email;

    @Schema(description = "Confirm Email of the user", example = "john.doe@example.com")
    @Email
    private String confirmEmail;

    @Schema(description = "Password of the user", example = "Password123!")
    private String pwd;

    @Schema(description = "Confirm Password of the user", example = "Password123!")
    private String confirmPwd;
}
