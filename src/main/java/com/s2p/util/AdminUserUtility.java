package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.model.AdminUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AdminUserUtility {

    public abstract AdminUsers toAdminUserEntity(AdminUserDto adminUserDto);
    public abstract AdminUserDto toAdminUserDto(AdminUsers adminUsers);

}
