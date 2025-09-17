package com.s2p.dto;

import com.s2p.model.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an Admin User")
public class AdminUserDto {

    @Schema(description = "Unique ID of the Admin User", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID AdminUserId;

    @Schema(description = "Email of the Admin User", example = "admin@example.com")
    private String email;

    @Schema(description = "Username of the Admin User", example = "admin123")
    private String username;

    @Schema(description = "Role assigned to the Admin User")
    private Roles roles;
}
