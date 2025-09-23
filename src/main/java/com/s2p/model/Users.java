package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * File Name: Users.java
 * Entity: Users (Base class)
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description: Base class for different types of user entities
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@Schema(description = "Base class representing a generic User")
public class Users extends BaseEntity {

	@Column(unique = true)
	@Schema(description = "Unique username of the user", example = "john_doe")
	private String username;

	@Column(unique = true)
	@Schema(description = "Unique email address of the user", example = "john@example.com")
	private String email;

	@Transient
	@Schema(description = "Confirmation of email for validation purposes", example = "john@example.com")
	private String confirmEmail;

	@Schema(description = "Password of the user", example = "securePassword123")
	private String pwd;

	@Transient
	@Schema(description = "Confirmation of password for validation purposes", example = "securePassword123")
	private String confirmPwd;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id", referencedColumnName = "rolesId")
	@Schema(description = "Role assigned to the user")
	private Roles roles;
}
