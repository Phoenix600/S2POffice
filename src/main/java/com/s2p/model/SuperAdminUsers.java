package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Entity representing a Super Admin user account")
public class SuperAdminUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the Super Admin user", example = "a12b34cd-56ef-78gh-90ij-klmnopqrstuv")
    private UUID superAdminUserId;

    @Schema(description = "Email of the Super Admin user", example = "superadmin@example.com")
    private String email;

    @Schema(description = "Username of the Super Admin user", example = "superadmin")
    private String username;
}
