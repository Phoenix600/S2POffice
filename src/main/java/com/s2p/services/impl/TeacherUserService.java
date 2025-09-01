package com.s2p.services.impl;

import com.s2p.dto.TeacherUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.Roles;
import com.s2p.model.TeacherUsers;
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

@Service
public class TeacherUserService implements ITeacherUserService
{
    @Autowired
    TeacherUserRepository teacherUserRepository;

    @Autowired
    TeacherUsersUtility teacherUsersUtility;

    @Autowired
    RolesUtility rolesUtility;

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

        // Update fields
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
}
