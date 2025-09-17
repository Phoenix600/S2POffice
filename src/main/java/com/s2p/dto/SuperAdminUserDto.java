package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Super Admin User")
public class SuperAdminUserDto {
    @Schema(description = "Unique ID of the super admin user", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID superAdminUserId;

    @Schema(description = "Email of the super admin user", example = "admin@example.com")
    private String email;

    @Schema(description = "Username of the super admin user", example = "admin_user")
    private String username;

    @Schema(description = "Roles assigned to the super admin user")
    private RolesDto roles;
}
