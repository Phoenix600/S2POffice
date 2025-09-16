package com.s2p.services.impl;

import com.s2p.dto.SuperAdminUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.Roles;
import com.s2p.model.SuperAdminUsers;
import com.s2p.repository.SuperAdminUserRepository;
import com.s2p.services.ISuperAdminUserService;
import com.s2p.util.RolesUtility;
import com.s2p.util.SuperAdminUserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuperAdminUserService implements ISuperAdminUserService
{
    @Autowired
    SuperAdminUserRepository superAdminUserRepository;

    @Autowired
    SuperAdminUserUtility superAdminUsersUtility;

    @Autowired
    RolesUtility rolesUtility;

    @Override
    public SuperAdminUserDto createSuperAdminUser(SuperAdminUserDto superAdminUserDto) {
        SuperAdminUsers entity = superAdminUsersUtility.toSuperAdminUserEntity(superAdminUserDto);

        if (superAdminUserDto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(superAdminUserDto.getRoles());
            entity.setRoles(roleEntity);
        }

        SuperAdminUsers saved = superAdminUserRepository.save(entity);
        return superAdminUsersUtility.toSuperAdminUserDto(saved);
    }

    @Override
    public Optional<SuperAdminUserDto> getSuperAdminUserByUsername(String username) {
        Optional<SuperAdminUsers> userOpt = superAdminUserRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            SuperAdminUserDto dto = superAdminUsersUtility.toSuperAdminUserDto(userOpt.get());
            return Optional.of(dto);
        }

        return Optional.empty();
    }


    @Override
    public Set<SuperAdminUserDto> getAllSuperAdminUsers()
    {
        List<SuperAdminUsers> superAdmins = superAdminUserRepository.findAll();
        Set<SuperAdminUserDto> result = new HashSet<>();
        for (SuperAdminUsers superAdmin : superAdmins) {
            result.add(superAdminUsersUtility.toSuperAdminUserDto(superAdmin));
        }
        return result;
    }

    @Override
    public SuperAdminUserDto updateSuperAdminUserByUsername(String username, SuperAdminUserDto superAdminUserDto) {
        Optional<SuperAdminUsers> userOpt = superAdminUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("SuperAdminUser not found with username: " + username);
        }

        SuperAdminUsers existingUser = userOpt.get();

        // Update fields
        existingUser.setEmail(superAdminUserDto.getEmail());
        existingUser.setUsername(superAdminUserDto.getUsername());

        if (superAdminUserDto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(superAdminUserDto.getRoles());
            existingUser.setRoles(roleEntity);
        }

        SuperAdminUsers updated = superAdminUserRepository.save(existingUser);
        return superAdminUsersUtility.toSuperAdminUserDto(updated);
    }

    @Override
    public void deleteSuperAdminUserByUsername(String username) {
        Optional<SuperAdminUsers> userOpt = superAdminUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("SuperAdminUser not found with username: " + username);
        }

        superAdminUserRepository.delete(userOpt.get());
    }

}
