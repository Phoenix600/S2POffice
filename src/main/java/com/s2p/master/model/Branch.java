package com.s2p.master.model;

import com.s2p.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Represents a branch within the organization.")
public class Branch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the branch.",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID branchId;

    @Column(nullable = false, length = 255)
    @Schema(description = "Name of the branch.",
            example = "Pune Campus")
    private String branchName;

}
