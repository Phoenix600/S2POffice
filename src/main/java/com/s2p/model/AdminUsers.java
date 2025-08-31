package com.s2p.model;

import com.s2p.dto.RolesDto;
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
@Schema(description = "Entity Representing an Admin Users")
public class AdminUsers extends Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the admin user.", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID AdminUserId;

    private String email;

    private String username;

    private Roles roles;

}
