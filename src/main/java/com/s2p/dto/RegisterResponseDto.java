package com.s2p.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterResponseDto
{
    private String username;
    private String email;
    private RolesDto rolesDto;
    private String token;
}
