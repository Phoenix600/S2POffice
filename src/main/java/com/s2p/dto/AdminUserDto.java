package com.s2p.dto;

import com.s2p.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminUserDto
{
    private UUID AdminUserId;

    private String email;

    private String username;

    private Roles roles;

}
