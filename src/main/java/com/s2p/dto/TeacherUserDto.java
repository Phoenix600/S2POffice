package com.s2p.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.Set;

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
    private Set<UUID> batchIds;
    private Set<UUID> courseIds;

}
