package com.s2p.services.impl;

import com.s2p.dto.RolesDto;
import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.Roles;
import com.s2p.model.TeacherUsers;
import com.s2p.repository.TeacherUserRepository;
import com.s2p.util.RolesUtility;
import com.s2p.util.TeacherUsersUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeacherUserServiceTest {

    @Mock
    private TeacherUserRepository teacherUserRepository;

    @Mock
    private TeacherUsersUtility teacherUsersUtility;

    @Mock
    private RolesUtility rolesUtility;

    @InjectMocks
    private TeacherUserService teacherUserService;

    private TeacherUsers teacherUser;
    private TeacherUserDto teacherUserDto;
    private Roles roles;
    private RolesDto rolesDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roles = new Roles();
        roles.setRolesId(UUID.randomUUID());
        roles.setRolesName("ROLE_TEACHER");

        rolesDto = new RolesDto(roles.getRolesId(), roles.getRolesName());

        teacherUser = new TeacherUsers();
        teacherUser.setTeacherUserId(UUID.randomUUID());
        teacherUser.setEmail("teacher@example.com");
        teacherUser.setUsername("teacherUser");
        teacherUser.setRoles(roles);

        teacherUserDto = new TeacherUserDto(
                teacherUser.getTeacherUserId(),
                teacherUser.getEmail(),
                teacherUser.getUsername(),
                rolesDto
        );
    }

    @Test
    void test_createTeacherUser_success() {
        when(teacherUsersUtility.toTeacherUsersEntity(any(TeacherUserDto.class))).thenReturn(teacherUser);
        when(rolesUtility.toRoles(any(RolesDto.class))).thenReturn(roles);
        when(teacherUserRepository.save(any(TeacherUsers.class))).thenReturn(teacherUser);
        when(teacherUsersUtility.toTeacherUsersDto(any(TeacherUsers.class))).thenReturn(teacherUserDto);

        TeacherUserDto result = teacherUserService.createTeacherUser(teacherUserDto);

        assertNotNull(result);
        assertEquals("teacherUser", result.getUsername());
        verify(teacherUserRepository, times(1)).save(any(TeacherUsers.class));
    }

    @Test
    void test_getAllTeachers_success() {
        List<TeacherUsers> teacherList = Collections.singletonList(teacherUser);
        when(teacherUserRepository.findAll()).thenReturn(teacherList);
        when(teacherUsersUtility.toTeacherUsersDto(any(TeacherUsers.class))).thenReturn(teacherUserDto);

        Set<TeacherUserDto> result = teacherUserService.getAllTeachers();

        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(dto -> "teacherUser".equals(dto.getUsername())));
    }

    @Test
    void test_getTeacherUserByUsername_found() {
        when(teacherUserRepository.findByUsername("teacherUser")).thenReturn(Optional.of(teacherUser));
        when(teacherUsersUtility.toTeacherUsersDto(any(TeacherUsers.class))).thenReturn(teacherUserDto);

        Optional<TeacherUserDto> result = teacherUserService.getTeacherUserByUsername("teacherUser");

        assertTrue(result.isPresent());
        assertEquals("teacherUser", result.get().getUsername());
    }

    @Test
    void test_getTeacherUserByUsername_notFound() {
        when(teacherUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        Optional<TeacherUserDto> result = teacherUserService.getTeacherUserByUsername("unknown");

        assertFalse(result.isPresent());
    }

    @Test
    void test_updateTeacherUserByUsername_success() {
        when(teacherUserRepository.findByUsername("teacherUser")).thenReturn(Optional.of(teacherUser));
        when(rolesUtility.toRoles(any(RolesDto.class))).thenReturn(roles);
        when(teacherUserRepository.save(any(TeacherUsers.class))).thenReturn(teacherUser);
        when(teacherUsersUtility.toTeacherUsersDto(any(TeacherUsers.class))).thenReturn(teacherUserDto);

        TeacherUserDto updateDto = new TeacherUserDto(
                teacherUser.getTeacherUserId(),
                "newteacher@example.com",
                "newTeacherUser",
                rolesDto
        );

        TeacherUserDto result = teacherUserService.updateTeacherUserByUsername("teacherUser", updateDto);

        assertNotNull(result);
        verify(teacherUserRepository, times(1)).save(any(TeacherUsers.class));
    }

    @Test
    void test_updateTeacherUserByUsername_notFound() {
        when(teacherUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> teacherUserService.updateTeacherUserByUsername("unknown", teacherUserDto));
    }

    @Test
    void test_deleteTeacherUserByUsername_success() {
        when(teacherUserRepository.findByUsername("teacherUser")).thenReturn(Optional.of(teacherUser));

        teacherUserService.deleteTeacherUserByUsername("teacherUser");

        verify(teacherUserRepository, times(1)).delete(teacherUser);
    }

    @Test
    void test_deleteTeacherUserByUsername_notFound() {
        when(teacherUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> teacherUserService.deleteTeacherUserByUsername("unknown"));
    }
}
