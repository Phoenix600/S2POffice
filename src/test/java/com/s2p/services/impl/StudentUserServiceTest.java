package com.s2p.services.impl;

import com.s2p.dto.StudentUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.StudentUsers;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.repository.StudentUserRepository;
import com.s2p.util.RolesUtility;
import com.s2p.util.StudentUsersUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentUserServiceTest {

    @Mock
    private StudentUserRepository studentUserRepository;

    @Mock
    private StudentUsersUtility studentUsersUtility;

    @Mock
    private RolesUtility rolesUtility;

    @Mock
    private StudentInformationRepository studentInformationRepository;

    @InjectMocks
    private StudentUserService studentUserService;

    private StudentUsers studentUser;
    private StudentUserDto studentUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        studentUser = new StudentUsers();
        studentUser.setStudentUserId(UUID.randomUUID());
        studentUser.setEmail("test@example.com");
        studentUser.setUsername("testUser");

        studentUserDto = new StudentUserDto();
        studentUserDto.setStudentUserId(studentUser.getStudentUserId());
        studentUserDto.setEmail(studentUser.getEmail());
        studentUserDto.setUsername(studentUser.getUsername());
    }

    @Test
    void test_createStudentUser_success() {
        when(studentUsersUtility.toStudentUserEntity(any(StudentUserDto.class))).thenReturn(studentUser);
        when(studentUserRepository.save(any(StudentUsers.class))).thenReturn(studentUser);
        when(studentUsersUtility.toStudentUserDto(any(StudentUsers.class))).thenReturn(studentUserDto);

        StudentUserDto result = studentUserService.createStudentUser(studentUserDto);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(studentUserRepository, times(1)).save(any(StudentUsers.class));
    }

    @Test
    void test_getStudentUserByUsername_found() {
        when(studentUserRepository.findByUsername("testUser")).thenReturn(Optional.of(studentUser));
        when(studentUsersUtility.toStudentUserDto(any(StudentUsers.class))).thenReturn(studentUserDto);

        Optional<StudentUserDto> result = studentUserService.getStudentUserByUsername("testUser");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
    }

    @Test
    void test_getStudentUserByUsername_notFound() {
        when(studentUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        Optional<StudentUserDto> result = studentUserService.getStudentUserByUsername("unknown");

        assertFalse(result.isPresent());
    }

    @Test
    void test_getAllStudentUsers_success() {
        List<StudentUsers> userList = Arrays.asList(studentUser);
        when(studentUserRepository.findAll()).thenReturn(userList);
        when(studentUsersUtility.toStudentUserDto(any(StudentUsers.class))).thenReturn(studentUserDto);

        List<StudentUserDto> result = studentUserService.getAllStudentUsers();

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void test_updateStudentUserByUsername_success() {
        when(studentUserRepository.findByUsername("testUser")).thenReturn(Optional.of(studentUser));
        when(studentUserRepository.save(any(StudentUsers.class))).thenReturn(studentUser);
        when(studentUsersUtility.toStudentUserDto(any(StudentUsers.class))).thenReturn(studentUserDto);
        when(studentUsersUtility.toStudentUserEntity(any(StudentUserDto.class))).thenReturn(studentUser);

        StudentUserDto updateDto = new StudentUserDto(studentUser.getStudentUserId(), "new@example.com", "newUser", null);
        StudentUserDto result = studentUserService.updateStudentUserByUsername("testUser", updateDto);

        assertNotNull(result);
        verify(studentUserRepository, times(1)).save(any(StudentUsers.class));
    }

    @Test
    void test_updateStudentUserByUsername_notFound() {
        when(studentUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> studentUserService.updateStudentUserByUsername("unknown", studentUserDto));
    }

    @Test
    void test_deleteStudentUserByUsername_success() {
        when(studentUserRepository.findByUsername("testUser")).thenReturn(Optional.of(studentUser));

        studentUserService.deleteStudentUserByUsername("testUser");

        verify(studentUserRepository, times(1)).delete(studentUser);
    }

    @Test
    void test_deleteStudentUserByUsername_notFound() {
        when(studentUserRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> studentUserService.deleteStudentUserByUsername("unknown"));
    }
}
