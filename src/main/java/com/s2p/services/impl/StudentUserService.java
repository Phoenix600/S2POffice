package com.s2p.services.impl;

import com.s2p.dto.StudentUserDto;
import com.s2p.exceptions.UserNotFoundException;
import com.s2p.model.StudentUsers;
import com.s2p.repository.StudentInformationRepository;
import com.s2p.repository.StudentUserRepository;
import com.s2p.services.IStudentUserService;
import com.s2p.util.RolesUtility;
import com.s2p.util.StudentUsersUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  StudentUserService implements IStudentUserService
{
    @Autowired
    StudentUserRepository studentUsersRepository;

    @Autowired
    StudentUsersUtility studentUsersUtility;

    @Autowired
    private RolesUtility rolesUtility;

    @Autowired
    StudentInformationRepository studentInformationRepository;

    @Override
    public StudentUserDto createStudentUser(StudentUserDto studentUsersDto) {
        StudentUsers entity = studentUsersUtility.toStudentUserEntity(studentUsersDto);
        StudentUsers saved = studentUsersRepository.save(entity);
        return studentUsersUtility.toStudentUserDto(saved);
    }

    @Override
    public Optional<StudentUserDto> getStudentUserByUsername(String username) {
        Optional<StudentUsers> studentUsersOptional = studentUsersRepository.findByUsername(username);

        if (studentUsersOptional.isPresent()) {
            StudentUserDto dto = studentUsersUtility.toStudentUserDto(studentUsersOptional.get());
            return Optional.of(dto);
        }

        return Optional.empty();
    }



    @Override
    public List<StudentUserDto> getAllStudentUsers() {
        List<StudentUsers> entities = studentUsersRepository.findAll();
        List<StudentUserDto> result = new ArrayList<>();

        for (StudentUsers studentUser : entities) {
            result.add(studentUsersUtility.toStudentUserDto(studentUser));
        }
        return result;
    }

    @Override
    public StudentUserDto updateStudentUserByUsername(String username, StudentUserDto studentUserDto) {
        Optional<StudentUsers> userOpt = studentUsersRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("StudentUser not found with username: " + username);
        }

        StudentUsers existingUser = userOpt.get();

        // Update fields
        existingUser.setEmail(studentUserDto.getEmail());
        existingUser.setUsername(studentUserDto.getUsername());

        if (studentUserDto.getRolesDto() != null) {
            existingUser.setRoles(studentUsersUtility.toStudentUserEntity(studentUserDto).getRoles());
        }

        StudentUsers updatedUser = studentUsersRepository.save(existingUser);
        return studentUsersUtility.toStudentUserDto(updatedUser);
    }

    @Override
    public void deleteStudentUserByUsername(String username) {
        Optional<StudentUsers> userOpt = studentUsersRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("StudentUser not found with username: " + username);
        }

        StudentUsers user = userOpt.get();
        studentUsersRepository.delete(user);
    }

}
