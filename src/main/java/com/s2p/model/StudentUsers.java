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
@Schema(description = "Entity representing a student user account")
public class StudentUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the student user", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private UUID studentUserId;

    @Schema(description = "Email of the student user", example = "student@example.com")
    private String email;

    @Schema(description = "Username of the student user", example = "john_doe")
    private String username;

    @Schema(description = "Role assigned to the student user")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "rolesId")
    private Roles roles;

}
