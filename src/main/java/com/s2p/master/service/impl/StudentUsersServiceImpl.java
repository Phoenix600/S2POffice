package com.s2p.master.service.impl;

import com.s2p.master.dto.StudentUsersDto;
import com.s2p.master.model.StudentUsers;
import com.s2p.master.repository.StudentUsersRepository;
import com.s2p.master.service.StudentUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentUsersServiceImpl implements StudentUsersService {

    @Autowired
    private StudentUsersRepository studentUsersRepository;

    @Override
    public StudentUsersDto createStudentUser(StudentUsersDto studentUsersDto) {
        StudentUsers studentUser = new StudentUsers();
        studentUser.setStudentName(studentUsersDto.getStudentName());
        studentUser.setEmail(studentUsersDto.getEmail());

        StudentUsers saved = studentUsersRepository.save(studentUser);

        StudentUsersDto response = new StudentUsersDto();
        response.setStudentUserId(saved.getStudentUserId());
        response.setStudentName(saved.getStudentName());
        response.setEmail(saved.getEmail());

        return response;
    }

    @Override
    public StudentUsersDto getStudentUserById(UUID studentUserId) {
        Optional<StudentUsers> optional = studentUsersRepository.findById(studentUserId);
        if (optional.isPresent()) {
            StudentUsers studentUser = optional.get();
            return new StudentUsersDto(
                    studentUser.getStudentUserId(),
                    studentUser.getStudentName(),
                    studentUser.getEmail()
            );
        }
        return null;
    }

    @Override
    public List<StudentUsersDto> getAllStudentUsers() {
        List<StudentUsers> studentUsersList = studentUsersRepository.findAll();
        List<StudentUsersDto> dtoList = new ArrayList<>();

        for (StudentUsers studentUser : studentUsersList) {
            StudentUsersDto dto = new StudentUsersDto(
                    studentUser.getStudentUserId(),
                    studentUser.getStudentName(),
                    studentUser.getEmail()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public StudentUsersDto updateStudentUser(UUID studentUserId, StudentUsersDto studentUsersDto) {
        Optional<StudentUsers> optional = studentUsersRepository.findById(studentUserId);
        if (optional.isPresent()) {
            StudentUsers studentUser = optional.get();
            studentUser.setStudentName(studentUsersDto.getStudentName());
            studentUser.setEmail(studentUsersDto.getEmail());

            StudentUsers updated = studentUsersRepository.save(studentUser);

            return new StudentUsersDto(
                    updated.getStudentUserId(),
                    updated.getStudentName(),
                    updated.getEmail()
            );
        }
        return null;
    }

    @Override
    public void deleteStudentUser(UUID studentUserId) {
        studentUsersRepository.deleteById(studentUserId);
    }
}
