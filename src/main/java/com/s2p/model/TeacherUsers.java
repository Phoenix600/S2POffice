package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Entity representing a Teacher user account")
public class TeacherUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the Teacher user", example = "b12c34de-56fg-78hi-90jk-lmnopqrstuvwx")
    private UUID teacherUserId;

    @Schema(description = "Email of the Teacher user", example = "teacher@example.com")
    private String email;

    @Schema(description = "Username of the Teacher user", example = "teacher123")
    private String username;

    @Schema(description = "Roles assigned to the Teacher user")
    private Roles roles;

    @Schema(description = "Batches assigned to the Teacher")
    @ManyToMany
    @JoinTable(
            name = "teacher_batch",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id")
    )
    private Set<Batch> batch = new HashSet<>();

    @Schema(description = "Courses assigned to the Teacher")
    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
