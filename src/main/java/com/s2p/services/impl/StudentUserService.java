package com.s2p.services.impl;

import com.s2p.dto.StudentUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.master.dto.StudentUsersDto;
import com.s2p.model.StudentUsers;
import com.s2p.repository.StudentUserRepository;
import com.s2p.services.IStudentUserService;
import com.s2p.util.StudentUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentUserService implements IStudentUserService
{
    @Autowired
    StudentUserRepository studentUserRepository;

    @Override
    public StudentUserDto createStudentUser(StudentUserDto studentUsersDto) {
        StudentUsers entity = StudentUsersUtility.toStudentUserEntity(studentUsersDto);
        StudentUsers saved = studentUserRepository.save(entity);
        return StudentUsersUtility.toStudentUserDto(saved);
    }

//    @Override
//    public StudentUserDto createStudentUser(StudentUsersDto studentUsersDto) {
//        return null;
//    }

    @Override
    public StudentUserDto getStudentUserById(UUID studentUserId) {
        Optional<StudentUsers> optional = studentUserRepository.findById(studentUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("StudentUser", "id", studentUserId.toString());
        }

        return StudentUsersUtility.toStudentUserDto(optional.get());
    }

    @Override
    public List<StudentUserDto> getAllStudentUsers() {
        List<StudentUsers> entities = studentUserRepository.findAll();
        List<StudentUserDto> result = new ArrayList<>();

        for (StudentUsers studentUser : entities) {
            result.add(StudentUsersUtility.toStudentUserDto(studentUser));
        }
        return result;
    }

    @Override
    public StudentUsersDto updateStudentUserById(UUID studentUserId) {
        return null;
    }

    @Override
    public StudentUserDto deleteStudentUserById(UUID studentUserId) {
        Optional<StudentUsers> optional = studentUserRepository.findById(studentUserId);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("StudentUser", "id", studentUserId.toString());
        }

        StudentUsers studentUser = optional.get();
        studentUserRepository.delete(studentUser);

        return StudentUsersUtility.toStudentUserDto(studentUser);
    }

}
