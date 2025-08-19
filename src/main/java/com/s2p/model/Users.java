package com.s2p.model;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;

/**
 * File Name: Users.java
 * Entity: Users
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

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
}
