package com.s2p.util;

import com.s2p.dto.RolesDto;
import com.s2p.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

/**
 * File Name: RolesUtility.java
 * Entity: RolesUtility
 * Package: com.s2p.util
 * Author: pranayramteke
 * Date: 23/08/25
 * Description:
 */


public abstract class RolesUtility
{
	public final static Roles toRoles(RolesDto rolesDto)
	{
		Roles roles = new Roles();
		roles.setRolesName(rolesDto.getRolesName());
		return roles;
	}
	
	public final static RolesDto toRolesDto(Roles roles)
	{
		RolesDto rolesDto = new RolesDto();
		return rolesDto;
	}

}

