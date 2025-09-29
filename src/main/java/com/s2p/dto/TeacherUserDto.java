package com.s2p.dto;

import com.s2p.model.Batch;
import com.s2p.model.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing a Teacher User")
public class TeacherUserDto {
    @Schema(description = "Unique ID of the teacher user", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID teacherUserId;

    @Schema(description = "Email of the teacher user", example = "teacher@example.com")
    private String email;

    @Schema(description = "Username of the teacher user", example = "teacher_jane")
    private String username;

    @Schema(description = "Roles assigned to the teacher user")
    private RolesDto roles;


    @Schema(description = "Batches assigned to the teacher")
    private Set<Batch> batch = new HashSet<>();

    @Schema(description = "Courses assigned to the teacher")
    private Set<Course> courses = new HashSet<>();
}
