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

public class Users extends BaseEntity {

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@Transient
	private String confirmEmail;

	@Column(nullable = false)
	private String pwd;

	@Transient
	private String confirmPwd;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id", referencedColumnName = "rolesId")
	private Roles roles;
}
