package com.s2p.master.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollegeDto {

    private UUID collegeId;

    @NotBlank(message = "College name is mandatory")
    private String collegeName;

    private Set<DepartmentDto> departmentSet = new LinkedHashSet<>();
}
