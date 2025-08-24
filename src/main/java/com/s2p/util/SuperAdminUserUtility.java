package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.model.AdminUsers;
import com.s2p.model.SuperAdminUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuperAdminUserUtility {

    public abstract SuperAdminUsers toSuperAdminUserEntity(SuperAdminUserDto superAdminUserDto);
    public abstract SuperAdminUserDto toSuperAdminUserDto(SuperAdminUsers superAdminUsers);

}

