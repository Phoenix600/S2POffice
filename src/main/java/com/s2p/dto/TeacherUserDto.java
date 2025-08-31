package com.s2p.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TeacherUserDto
{
    private UUID teacherUserId;

    private String email;

    private String username;

    private RolesDto roles;
}
