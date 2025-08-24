package com.s2p.master.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentUsersDto {

    private UUID studentUserId;

    @NotBlank(message = "Student name is mandatory")
    private String studentName;

    @NotBlank(message = "Email is mandatory")
    private String email;
}
