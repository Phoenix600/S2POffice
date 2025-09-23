package com.s2p.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Student User")
public class StudentUserDto {
    @Schema(description = "Unique ID of the student user", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID studentUserId;

    @Schema(description = "Email of the student user", example = "student@example.com")
    private String email;

    @Schema(description = "Username of the student user", example = "john_doe")
    private String username;

    @Schema(description = "Roles assigned to the student user")
    private RolesDto roles;
}
