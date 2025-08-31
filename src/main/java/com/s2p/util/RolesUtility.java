package com.s2p.util;

import com.s2p.dto.RolesDto;
import com.s2p.model.Roles;
import org.mapstruct.Mapper;

/**
 * File Name: RolesUtility.java
 * Entity: RolesUtility
 * Package: com.s2p.util
 * Author: pranayramteke
 * Date: 23/08/25
 * Description:
 */

@Mapper(componentModel = "spring")
public interface RolesUtility
{
	public abstract Roles toRoles(RolesDto rolesDto);
	public abstract RolesDto toRolesDto(Roles roles);

}

