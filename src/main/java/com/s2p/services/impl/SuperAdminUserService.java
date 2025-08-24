package com.s2p.services.impl;

import com.s2p.dto.SuperAdminUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.SuperAdminUsers;
import com.s2p.repository.SuperAdminUserRepository;
import com.s2p.services.ISuperAdminUserService;
import com.s2p.util.SuperAdminUserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuperAdminUserService implements ISuperAdminUserService
{
    @Autowired
    SuperAdminUserRepository superAdminUserRepository;

    @Override
    public SuperAdminUserDto createSuperAdminUser(SuperAdminUserDto superAdminUserDto) {
        SuperAdminUsers entity = SuperAdminUserUtility.toSuperAdminUserEntity(superAdminUserDto);
        SuperAdminUsers saved = superAdminUserRepository.save(entity);
        return SuperAdminUserUtility.toSuperAdminUserDto(saved);
    }

    @Override
    public SuperAdminUserDto getSuperAdminUserById(UUID superAdminUserId) {
        Optional<SuperAdminUsers> optional = superAdminUserRepository.findById(superAdminUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("SuperAdminUser", "id", superAdminUserId.toString());
        }

        return SuperAdminUserUtility.toSuperAdminUserDto(optional.get());
    }

    @Override
    public Set<SuperAdminUserDto> getAllSuperAdminUsers()
    {
        List<SuperAdminUsers> superAdmins = superAdminUserRepository.findAll();
        Set<SuperAdminUserDto> result = new HashSet<>();
        for (SuperAdminUsers superAdmin : superAdmins) {
            result.add(SuperAdminUserUtility.toSuperAdminUserDto(superAdmin));
        }
        return result;
    }

    @Override
    public SuperAdminUserDto updateSuperAdminUserById(UUID superAdminUserId) {
        return null;
    }

    @Override
    public SuperAdminUserDto deleteSuperAdminById(UUID superAdminUserId) {
        Optional<SuperAdminUsers> optional = superAdminUserRepository.findById(superAdminUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("SuperAdminUser", "id", superAdminUserId.toString());
        }

        SuperAdminUsers entity = optional.get();
        superAdminUserRepository.delete(entity);
        return SuperAdminUserUtility.toSuperAdminUserDto(entity);
    }
}
