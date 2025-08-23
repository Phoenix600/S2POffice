package com.s2p.master.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "student_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_user_id")
    private UUID studentUserId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
