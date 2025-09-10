package com.s2p.services.impl;

import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.Batch;
import com.s2p.model.Course;
import com.s2p.model.Roles;
import com.s2p.model.TeacherUsers;
import com.s2p.repository.BatchRepository;
import com.s2p.repository.CourseRepository;
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

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private TeacherUserService teacherUserService;

    private TeacherUsers teacherUser;
    private TeacherUserDto teacherUserDto;
    private Roles roles;
    private UUID batchId;
    private Batch batch;
    private UUID courseId;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roles = new Roles();
        roles.setRolesId(UUID.randomUUID());
        roles.setRolesName("ROLE_TEACHER");

        teacherUser = new TeacherUsers();
        teacherUser.setTeacherUserId(UUID.randomUUID());
        teacherUser.setEmail("teacher@example.com");
        teacherUser.setUsername("teacherUser");
        teacherUser.setRoles(roles);

        teacherUserDto = new TeacherUserDto();
        teacherUserDto.setTeacherUserId(teacherUser.getTeacherUserId());
        teacherUserDto.setEmail(teacherUser.getEmail());
        teacherUserDto.setUsername(teacherUser.getUsername());
        teacherUserDto.setRoles(null);

        batchId = UUID.randomUUID();
        batch = new Batch();
        batch.setBatchId(batchId);
        batch.setBatchName("Batch A");

        courseId = UUID.randomUUID();
        course = new Course();
        course.setCourseId(courseId);
        course.setCourseName("Course 101");
    }

    @Test
    void test_getTeachersByBatch_success() {
        when(teacherUserRepository.findByBatch_BatchId(batchId)).thenReturn(Collections.singletonList(teacherUser));
        when(teacherUsersUtility.toTeacherUsersDto(any())).thenReturn(teacherUserDto);

        Set<TeacherUserDto> result = teacherUserService.getTeachersByBatch(batchId);

        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(dto -> "teacherUser".equals(dto.getUsername())));
    }

    @Test
    void test_getTeachersByBatch_notFound() {
        when(teacherUserRepository.findByBatch_BatchId(batchId)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class,
                () -> teacherUserService.getTeachersByBatch(batchId));
    }

    @Test
    void test_updateTeacherByBatch_success() {
        when(teacherUserRepository.findById(teacherUser.getTeacherUserId())).thenReturn(Optional.of(teacherUser));
        when(batchRepository.findById(batchId)).thenReturn(Optional.of(batch));
        when(rolesUtility.toRoles(any())).thenReturn(roles);
        when(teacherUserRepository.save(any())).thenReturn(teacherUser);
        when(teacherUsersUtility.toTeacherUsersDto(any())).thenReturn(teacherUserDto);

        TeacherUserDto result = teacherUserService.updateTeacherByBatch(batchId, teacherUser.getTeacherUserId(), teacherUserDto);

        assertNotNull(result);
        verify(teacherUserRepository, times(1)).save(any());
    }

    @Test
    void test_removeTeacherFromBatch_success() {
        when(teacherUserRepository.findById(teacherUser.getTeacherUserId())).thenReturn(Optional.of(teacherUser));
        when(batchRepository.findById(batchId)).thenReturn(Optional.of(batch));

        teacherUserService.removeTeacherFromBatch(batchId, teacherUser.getTeacherUserId());

        verify(teacherUserRepository, times(1)).save(any());
    }

    @Test
    void test_getTeachersByCourse_success() {
        when(teacherUserRepository.findByCourses_CourseId(courseId)).thenReturn(Collections.singletonList(teacherUser));
        when(teacherUsersUtility.toTeacherUsersDto(any())).thenReturn(teacherUserDto);

        Set<TeacherUserDto> result = teacherUserService.getTeachersByCourse(courseId);

        assertEquals(1, result.size());
    }

    @Test
    void test_updateTeacherByCourse_success() {
        when(teacherUserRepository.findById(teacherUser.getTeacherUserId())).thenReturn(Optional.of(teacherUser));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(rolesUtility.toRoles(any())).thenReturn(roles);
        when(teacherUserRepository.save(any())).thenReturn(teacherUser);
        when(teacherUsersUtility.toTeacherUsersDto(any())).thenReturn(teacherUserDto);

        TeacherUserDto result = teacherUserService.updateTeacherByCourse(courseId, teacherUser.getTeacherUserId(), teacherUserDto);

        assertNotNull(result);
        verify(teacherUserRepository, times(1)).save(any());
    }

    @Test
    void test_removeTeacherFromCourse_success() {
        when(teacherUserRepository.findById(teacherUser.getTeacherUserId())).thenReturn(Optional.of(teacherUser));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        teacherUserService.removeTeacherFromCourse(courseId, teacherUser.getTeacherUserId());

        verify(teacherUserRepository, times(1)).save(any());
    }

    @Test
    void test_deleteTeacherById_success() {
        when(teacherUserRepository.existsById(teacherUser.getTeacherUserId())).thenReturn(true);

        teacherUserService.deleteTeacherById(teacherUser.getTeacherUserId());

        verify(teacherUserRepository, times(1)).deleteById(teacherUser.getTeacherUserId());
    }

    @Test
    void test_deleteTeacherById_notFound() {
        when(teacherUserRepository.existsById(teacherUser.getTeacherUserId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> teacherUserService.deleteTeacherById(teacherUser.getTeacherUserId()));
    }
}
