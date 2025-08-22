package com.s2p.util;

import com.s2p.dto.AdminUserDto;
import com.s2p.model.AdminUsers;

public class AdminUserUtility {

    public static AdminUsers toAdminUserEntity(AdminUserDto adminUserDto) {
        AdminUsers adminUsers = new AdminUsers();
        adminUsers.setAdminUserId(adminUserDto.getAdminUserId());
        return adminUsers;
    }

    public static AdminUserDto toAdminUserDto(AdminUsers adminUsers) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setAdminUserId(adminUsers.getAdminUserId());
        return adminUserDto;
    }
}
