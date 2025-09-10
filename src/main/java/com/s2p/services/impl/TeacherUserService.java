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
import com.s2p.services.ITeacherUserService;
import com.s2p.util.RolesUtility;
import com.s2p.util.TeacherUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.List;

@Service
public class TeacherUserService implements ITeacherUserService
{
    @Autowired
    TeacherUserRepository teacherUserRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    TeacherUsersUtility teacherUsersUtility;

    @Autowired
    RolesUtility rolesUtility;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public TeacherUserDto createTeacherUser(TeacherUserDto teacherUserDto) {
        TeacherUsers entity = teacherUsersUtility.toTeacherUsersEntity(teacherUserDto);

        if (teacherUserDto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(teacherUserDto.getRoles());
            entity.setRoles(roleEntity);
        }

        TeacherUsers saved = teacherUserRepository.save(entity);
        return teacherUsersUtility.toTeacherUsersDto(saved);
    }

    @Override
    public Set<TeacherUserDto> getAllTeachers() {
        Set<TeacherUserDto> result = new HashSet<>();
        Iterable<TeacherUsers> teachers = teacherUserRepository.findAll();

        for (TeacherUsers teacher : teachers) {
            TeacherUserDto dto = teacherUsersUtility.toTeacherUsersDto(teacher);
            result.add(dto);
        }

        return result;
    }

    @Override
    public Optional<TeacherUserDto> getTeacherUserByUsername(String username) {
        Optional<TeacherUsers> userOpt = teacherUserRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            TeacherUserDto dto = teacherUsersUtility.toTeacherUsersDto(userOpt.get());
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    @Override
    public TeacherUserDto updateTeacherUserByUsername(String username, TeacherUserDto teacherUserDto) {
        Optional<TeacherUsers> userOpt = teacherUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("TeacherUser not found with username: " + username);
        }

        TeacherUsers existingUser = userOpt.get();

        existingUser.setEmail(teacherUserDto.getEmail());
        existingUser.setUsername(teacherUserDto.getUsername());

        if (teacherUserDto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(teacherUserDto.getRoles());
            existingUser.setRoles(roleEntity);
        }

        TeacherUsers updatedUser = teacherUserRepository.save(existingUser);
        return teacherUsersUtility.toTeacherUsersDto(updatedUser);
    }

    @Override
    public void deleteTeacherUserByUsername(String username) {
        Optional<TeacherUsers> userOpt = teacherUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("TeacherUser not found with username: " + username);
        }

        teacherUserRepository.delete(userOpt.get());
    }

    @Override
    public Set<TeacherUserDto> getTeachersByBatch(UUID batchId) {
        List<TeacherUsers> teachers = teacherUserRepository.findByBatch_BatchId(batchId);

        if (teachers.isEmpty()) {
            throw new ResourceNotFoundException("No teachers found for batch id: " + batchId);
        }

        Set<TeacherUserDto> result = new HashSet<>();
        for (TeacherUsers teacher : teachers) {
            result.add(teacherUsersUtility.toTeacherUsersDto(teacher));
        }

        return result;
    }

    // Update a teacher (email/username/roles) and assign them to a batch
    @Override
    public TeacherUserDto updateTeacherByBatch(UUID batchId, UUID teacherId, TeacherUserDto dto) {
        TeacherUsers teacher = teacherUserRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Batch batch =  batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));

        teacher.setEmail(dto.getEmail());
        teacher.setUsername(dto.getUsername());

        if (dto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(dto.getRoles());
            teacher.setRoles(roleEntity);
        }

        teacher.getBatch().add(batch); // link teacher to batch
        TeacherUsers updated = teacherUserRepository.save(teacher);

        return teacherUsersUtility.toTeacherUsersDto(updated);
    }

    // Remove a teacher from a batch (but not delete teacher completely)
    @Override
    public void removeTeacherFromBatch(UUID batchId, UUID teacherId) {
        TeacherUsers teacher = teacherUserRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));

        teacher.getBatch().remove(batch);
        teacherUserRepository.save(teacher);
    }


    @Override
    public Set<TeacherUserDto> getTeachersByCourse(UUID courseId) {
        List<TeacherUsers> teachers = teacherUserRepository.findByCourses_CourseId(courseId);

        if (teachers.isEmpty()) {
            throw new ResourceNotFoundException("No teachers found for course id: " + courseId);
        }

        Set<TeacherUserDto> result = new HashSet<>();
        for (TeacherUsers teacher : teachers) {
            result.add(teacherUsersUtility.toTeacherUsersDto(teacher));
        }

        return result;
    }

    // Update a teacher (email/username/roles) and assign them to a course
    @Override
    public TeacherUserDto updateTeacherByCourse(UUID courseId, UUID teacherId, TeacherUserDto dto) {
        TeacherUsers teacher = teacherUserRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        teacher.setEmail(dto.getEmail());
        teacher.setUsername(dto.getUsername());

        if (dto.getRoles() != null) {
            Roles roleEntity = rolesUtility.toRoles(dto.getRoles());
            teacher.setRoles(roleEntity);
        }

        teacher.getCourses().add(course); // link teacher to course
        TeacherUsers updated = teacherUserRepository.save(teacher);

        return teacherUsersUtility.toTeacherUsersDto(updated);
    }

    // Remove a teacher from a course (but not delete teacher completely)
    @Override
    public void removeTeacherFromCourse(UUID courseId, UUID teacherId) {
        TeacherUsers teacher = teacherUserRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        teacher.getCourses().remove(course);
        teacherUserRepository.save(teacher);
    }

    // Delete a teacher completely (by teacherId)
    @Override
    public void deleteTeacherById(UUID teacherId) {
        if (!teacherUserRepository.existsById(teacherId)) {
            throw new ResourceNotFoundException("Teacher not found");
        }
        teacherUserRepository.deleteById(teacherId);
    }


}
