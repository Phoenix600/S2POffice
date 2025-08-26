package com.s2p.services.impl;

import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.TeacherUsers;
import com.s2p.repository.TeacherUserRepository;
import com.s2p.services.ITeacherUserService;
import com.s2p.util.TeacherUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TeacherUserService implements ITeacherUserService
{
    @Autowired
    TeacherUserRepository teacherUserRepository;

    @Autowired
    TeacherUsersUtility teacherUsersUtility;

    @Override
    public TeacherUserDto createTeacherUser(TeacherUserDto teacherUserDto) {
        TeacherUsers teacher = teacherUsersUtility.toTeacherUsersEntity(teacherUserDto);
        TeacherUsers saved = teacherUserRepository.save(teacher);
        return teacherUsersUtility.toTeacherUsersDto(saved);
    }

    @Override
    public TeacherUserDto getTeacherById(UUID teacherUserId) {
        Optional<TeacherUsers> optionalTeacher = teacherUserRepository.findById(teacherUserId);
        if (optionalTeacher.isEmpty()) {
            throw new ResourceNotFoundException("TeacherUser", "id", teacherUserId.toString());
        }
        return teacherUsersUtility.toTeacherUsersDto(optionalTeacher.get());
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
    public TeacherUserDto updateTeacherUserById(UUID teacherUserId) {
        return null;
    }

    @Override
    public TeacherUserDto deleteTeacherUserById(UUID teacherUserId) {
        Optional<TeacherUsers> optionalTeacher = teacherUserRepository.findById(teacherUserId);
        if (optionalTeacher.isEmpty()) {
            throw new ResourceNotFoundException("TeacherUser", "id", teacherUserId.toString());
        }

        TeacherUsers teacher = optionalTeacher.get();
        teacherUserRepository.delete(teacher);
        return teacherUsersUtility.toTeacherUsersDto(teacher);
    }
}
