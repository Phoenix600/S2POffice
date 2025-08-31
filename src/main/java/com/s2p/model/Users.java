package com.s2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File Name: Users.java
 * Entity: Users
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Users extends BaseEntity
{
	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@Transient
	private String confirmEmail;

	private String pwd;

	@Transient
	private String confirmPwd;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id", referencedColumnName = "rolesId")
	private Roles roles;

}