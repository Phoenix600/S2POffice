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

public class TeacherUsers extends Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID teacherUserId;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private Roles roles;

    @ManyToMany
    @JoinTable(
            name = "teacher_batch",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id")
    )
    private Set<Batch> batch = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
