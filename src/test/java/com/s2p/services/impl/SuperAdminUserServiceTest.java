package com.s2p.services.impl;

import com.s2p.dto.RolesDto;
import com.s2p.dto.SuperAdminUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.Roles;
import com.s2p.model.SuperAdminUsers;
import com.s2p.repository.SuperAdminUserRepository;
import com.s2p.util.RolesUtility;
import com.s2p.util.SuperAdminUserUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SuperAdminUserServiceTest {

    @Mock
    private SuperAdminUserRepository superAdminUserRepository;

    @Mock
    private SuperAdminUserUtility superAdminUserUtility;

    @Mock
    private RolesUtility rolesUtility;

    @InjectMocks
    private SuperAdminUserService superAdminUserService;

    private SuperAdminUsers superAdminUser;
    private SuperAdminUserDto superAdminUserDto;
    private Roles roles;
    private RolesDto rolesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roles = new Roles();
        rolesDto = new RolesDto();

        superAdminUser = new SuperAdminUsers();
        superAdminUser.setSuperAdminUserId(UUID.randomUUID());
        superAdminUser.setEmail("admin@example.com");
        superAdminUser.setUsername("superAdmin");
        superAdminUser.setRoles(roles);

        superAdminUserDto = new SuperAdminUserDto();
        superAdminUserDto.setSuperAdminUserId(superAdminUser.getSuperAdminUserId());
        superAdminUserDto.setEmail(superAdminUser.getEmail());
        superAdminUserDto.setUsername(superAdminUser.getUsername());
        superAdminUserDto.setRoles(rolesDto);
    }

    @Test
    void test_createSuperAdminUser_success() {
        when(superAdminUserUtility.toSuperAdminUserEntity(any(SuperAdminUserDto.class))).thenReturn(superAdminUser);
        when(rolesUtility.toRoles(any(RolesDto.class))).thenReturn(roles);
        when(superAdminUserRepository.save(any(SuperAdminUsers.class))).thenReturn(superAdminUser);
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        SuperAdminUserDto result = superAdminUserService.createSuperAdminUser(superAdminUserDto);

        assertNotNull(result);
        assertEquals("superAdmin", result.getUsername());
        verify(superAdminUserRepository, times(1)).save(any(SuperAdminUsers.class));
    }

    @Test
    void test_createSuperAdminUser_withoutRole() {
        superAdminUserDto.setRoles(null);

        when(superAdminUserUtility.toSuperAdminUserEntity(any(SuperAdminUserDto.class))).thenReturn(superAdminUser);
        when(superAdminUserRepository.save(any(SuperAdminUsers.class))).thenReturn(superAdminUser);
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        SuperAdminUserDto result = superAdminUserService.createSuperAdminUser(superAdminUserDto);

        assertNotNull(result);
        assertEquals("superAdmin", result.getUsername());
        verify(rolesUtility, never()).toRoles(any());
    }

    @Test
    void test_getSuperAdminUserByUsername_found() {
        when(superAdminUserRepository.findByUsername("superAdmin")).thenReturn(Optional.of(superAdminUser));
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        Optional<SuperAdminUserDto> result = superAdminUserService.getSuperAdminUserByUsername("superAdmin");

        assertTrue(result.isPresent());
        assertEquals("superAdmin", result.get().getUsername());
    }

    @Test
    void test_getSuperAdminUserByUsername_notFound() {
        when(superAdminUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        Optional<SuperAdminUserDto> result = superAdminUserService.getSuperAdminUserByUsername("unknown");

        assertFalse(result.isPresent());
    }

    @Test
    void test_getAllSuperAdminUsers_success() {
        when(superAdminUserRepository.findAll()).thenReturn(Collections.singletonList(superAdminUser));
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        Set<SuperAdminUserDto> result = superAdminUserService.getAllSuperAdminUsers();

        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(dto -> dto.getUsername().equals("superAdmin")));
    }

    @Test
    void test_getAllSuperAdminUsers_empty() {
        when(superAdminUserRepository.findAll()).thenReturn(Collections.emptyList());

        Set<SuperAdminUserDto> result = superAdminUserService.getAllSuperAdminUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void test_updateSuperAdminUserByUsername_success() {
        when(superAdminUserRepository.findByUsername("superAdmin")).thenReturn(Optional.of(superAdminUser));
        when(rolesUtility.toRoles(any(RolesDto.class))).thenReturn(roles);
        when(superAdminUserRepository.save(any(SuperAdminUsers.class))).thenReturn(superAdminUser);
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        SuperAdminUserDto updateDto = new SuperAdminUserDto(superAdminUser.getSuperAdminUserId(),
                "newadmin@example.com", "newAdmin", rolesDto);

        SuperAdminUserDto result = superAdminUserService.updateSuperAdminUserByUsername("superAdmin", updateDto);

        assertNotNull(result);
        verify(superAdminUserRepository, times(1)).save(any(SuperAdminUsers.class));
    }

    @Test
    void test_updateSuperAdminUserByUsername_noRoles() {
        when(superAdminUserRepository.findByUsername("superAdmin")).thenReturn(Optional.of(superAdminUser));
        when(superAdminUserRepository.save(any(SuperAdminUsers.class))).thenReturn(superAdminUser);
        when(superAdminUserUtility.toSuperAdminUserDto(any(SuperAdminUsers.class))).thenReturn(superAdminUserDto);

        superAdminUserDto.setRoles(null);
        SuperAdminUserDto result = superAdminUserService.updateSuperAdminUserByUsername("superAdmin", superAdminUserDto);

        assertNotNull(result);
        verify(rolesUtility, never()).toRoles(any());
    }

    @Test
    void test_updateSuperAdminUserByUsername_notFound() {
        when(superAdminUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> superAdminUserService.updateSuperAdminUserByUsername("unknown", superAdminUserDto));
    }

    @Test
    void test_deleteSuperAdminUserByUsername_success() {
        when(superAdminUserRepository.findByUsername("superAdmin")).thenReturn(Optional.of(superAdminUser));

        superAdminUserService.deleteSuperAdminUserByUsername("superAdmin");

        verify(superAdminUserRepository, times(1)).delete(superAdminUser);
    }

    @Test
    void test_deleteSuperAdminUserByUsername_notFound() {
        when(superAdminUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> superAdminUserService.deleteSuperAdminUserByUsername("unknown"));

        verify(superAdminUserRepository, never()).delete(any());
    }
}
