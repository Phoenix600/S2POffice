package com.s2p.master.dto;

import com.s2p.master.model.College;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDto
{
    private UUID departmentId;

    @Schema(description = "Name of the department.",
            example = "Computer Science")
    private String departmentName;

    @Schema(description = "Set of colleges associated with this department.")
    private Set<College> college = new HashSet<>();
}
