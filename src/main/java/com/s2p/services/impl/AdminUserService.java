package com.s2p.services.impl;

import com.s2p.dto.AdminUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.AdminUsers;
import com.s2p.repository.AdminUsersRepository;
import com.s2p.services.IAdminUserService;
import com.s2p.util.AdminUserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminUserService implements IAdminUserService
{
    @Autowired
    AdminUsersRepository adminUsersRepository;


    @Override
    public AdminUserDto createAdminUser(AdminUserDto adminUserDto) {
        AdminUsers entity = AdminUserUtility.toAdminUserEntity(adminUserDto);
        AdminUsers saved = adminUsersRepository.save(entity);
        return AdminUserUtility.toAdminUserDto(saved);
    }

    @Override
    public AdminUserDto getAdminUserById(UUID adminUserId) {
        Optional<AdminUsers> optional = adminUsersRepository.findById(adminUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("AdminUser", "id", adminUserId.toString());
        }
        return AdminUserUtility.toAdminUserDto(optional.get());
    }

    @Override
    public Set<AdminUserDto> getAllAdinUsers() {
        List<AdminUsers> adminUsers = adminUsersRepository.findAll();
        Set<AdminUserDto> result = new HashSet<>();

        for (AdminUsers user : adminUsers) {
            result.add(AdminUserUtility.toAdminUserDto(user));
        }

        return result;
    }

    @Override
    public AdminUserDto updateAdminUserById(UUID adminUserId, AdminUserDto adminUserDto) {
        return null;
    }

    @Override
    public AdminUserDto deleteAdminUserById(UUID adminUserId) {
        Optional<AdminUsers> optional = adminUsersRepository.findById(adminUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("AdminUser", "id", adminUserId.toString());
        }

        AdminUsers entity = optional.get();
        adminUsersRepository.delete(entity);

        return AdminUserUtility.toAdminUserDto(entity);
    }
}
