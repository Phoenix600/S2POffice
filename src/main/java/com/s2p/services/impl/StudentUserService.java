package com.s2p.services.impl;

import com.s2p.dto.StudentUserDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.StudentUsers;
import com.s2p.repository.StudentUserRepository;
import com.s2p.services.IStudentUserService;
import com.s2p.util.StudentUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentUserService implements IStudentUserService
{
    @Autowired
    StudentUserRepository studentUserRepository;

    @Autowired
    StudentUsersUtility studentUsersUtility;

    @Override
    public StudentUserDto createStudentUser(StudentUserDto studentUsersDto) {
        StudentUsers entity = studentUsersUtility.toStudentUserEntity(studentUsersDto);
        StudentUsers saved = studentUserRepository.save(entity);
        return studentUsersUtility.toStudentUserDto(saved);
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

        return studentUsersUtility.toStudentUserDto(optional.get());
    }

    @Override
    public List<StudentUserDto> getAllStudentUsers() {
        List<StudentUsers> entities = studentUserRepository.findAll();
        List<StudentUserDto> result = new ArrayList<>();

        for (StudentUsers studentUser : entities) {
            result.add(studentUsersUtility.toStudentUserDto(studentUser));
        }
        return result;
    }

    @Override
    public StudentUserDto updateStudentUserById(UUID studentUserId) {
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

        return studentUsersUtility.toStudentUserDto(studentUser);
    }

}
