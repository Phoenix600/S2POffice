package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Entity representing a Role assigned to a user")
public class Roles extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Schema(description = "Unique identifier for the role", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
	private UUID rolesId;

	@Schema(description = "Name of the role", example = "ADMIN")
	private String rolesName;
}
