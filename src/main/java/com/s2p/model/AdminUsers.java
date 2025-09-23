package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        name = "AdminUsers",
        description = "Entity representing an administrative user with roles and login details."
)
public class AdminUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(
            description = "Unique identifier for the Admin User",
            example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID adminUserId;

    @Schema(
            description = "Email address of the admin user",
            example = "admin@example.com",
            required = true
    )
    private String email;

    @Schema(
            description = "Username chosen by the admin user",
            example = "admin123",
            required = true
    )
    private String username;

    @Schema(
            description = "Role assigned to the admin user",
            example = "SUPER_ADMIN"
    )
    private Roles roles;
}
