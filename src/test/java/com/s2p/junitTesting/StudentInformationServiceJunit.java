package com.s2p.junitTesting;

import com.s2p.dto.StudentInformationDto;
import com.s2p.model.StudentInformation;
import com.s2p.services.IStudentInformationService;
import com.s2p.util.StudentInformationUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentInformationServiceJunit {

    @Autowired
    IStudentInformationService studentInformationService;

    @Autowired
    StudentInformationUtility studentInformationUtility;


    @Test
    void createStudent() {

        StudentInformationDto studentInformationDto = new StudentInformationDto();
        studentInformationDto.setFirstName("Abcd");
        studentInformationDto.setLastName("Xyz");
        studentInformationDto.setEmail("abc@gmail.com");
        studentInformationDto.setCollegeName("HIJ College");
        studentInformationDto.setDepartName("CSE");
        studentInformationDto.setSemester(Integer.toString(5));
        studentInformationDto.setPassingYear(Integer.toString(2025));

        var savedStudent =  studentInformationService.createStudent(studentInformationDto);
        System.out.println(savedStudent);
//        StudentInformation savedStudent = studentInformationUtility.toStudentInformationEntity(studentInformationDto);

    }

    @Test
    void updateStudent() {

    }

    @Test
    void deleteStudent() {

    }

    @Test
    void getStudentByEmail() {

    }

    @Test
    void getAllStudents() {

    }
}
