package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.model.AdminUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class AdminUserUtility {

    public final static AdminUsers toAdminUserEntity(AdminUserDto adminUserDto) {
        AdminUsers adminUsers = new AdminUsers();
        adminUsers.setAdminUserId(adminUserDto.getAdminUserId());
        return adminUsers;
    }

    public final static AdminUserDto toAdminUserDto(AdminUsers adminUsers) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setAdminUserId(adminUsers.getAdminUserId());
        return adminUserDto;
    }
}
