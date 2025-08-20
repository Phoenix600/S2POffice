package com.s2p.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersDto {
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Transient
    private String confirmEmail;

    private String pwd;

    @Transient
    private String confirmPwd;
}
