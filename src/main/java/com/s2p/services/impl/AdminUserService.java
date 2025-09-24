package com.s2p.services.impl;

import com.s2p.dto.AdminUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.AdminUsers;
import com.s2p.repository.AdminUsersRepository;
import com.s2p.services.IAdminUserService;
import com.s2p.util.AdminUserUtility;
import com.s2p.util.RolesUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminUserService implements IAdminUserService
{
    @Autowired
    AdminUsersRepository adminUsersRepository;

    @Autowired
    AdminUserUtility adminUserUtility;

    @Autowired
    RolesUtility rolesUtility;

    @Override
    public AdminUserDto createAdminUser(AdminUserDto adminUserDto) {
        AdminUsers entity = adminUserUtility.toAdminUserEntity(adminUserDto);
        AdminUsers saved = adminUsersRepository.save(entity);
        return adminUserUtility.toAdminUserDto(saved);
    }

    @Override
    public AdminUserDto getAdminUserByUsername(String username) {
        AdminUsers entity = adminUsersRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser not found with username: " + username));
        return adminUserUtility.toAdminUserDto(entity);
    }


    @Override
    public Set<AdminUserDto> getAllAdminUsers() {
        List<AdminUsers> adminUsers = adminUsersRepository.findAll();
        Set<AdminUserDto> result = new HashSet<>();

        for (AdminUsers user : adminUsers) {
            result.add(adminUserUtility.toAdminUserDto(user));
        }

        return result;
    }

    @Override
    public AdminUserDto updateAdminUserByUsername(String username, AdminUserDto adminUserDto) {
        AdminUsers existing = adminUsersRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser not found with username: " + username));

        existing.setEmail(adminUserDto.getEmail());
        existing.setUsername(adminUserDto.getUsername());
        existing.setRoles(rolesUtility.toRoles(adminUserDto.getRolesDto()));

        AdminUsers updated = adminUsersRepository.save(existing);
        return adminUserUtility.toAdminUserDto(updated);
    }

    @Override
    public AdminUserDto deleteAdminUserByUsername(String username) {
        AdminUsers entity = adminUsersRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("AdminUser not found with username: " + username));

        AdminUserDto dto = adminUserUtility.toAdminUserDto(entity);
        adminUsersRepository.delete(entity);

        return dto;
    }
}
