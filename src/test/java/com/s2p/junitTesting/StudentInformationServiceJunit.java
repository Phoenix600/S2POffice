package com.s2p.junitTesting;

import com.s2p.dto.StudentInformationDto;
import com.s2p.services.IStudentInformationService;
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

    private StudentInformationDto buildStudent(String email) {
        StudentInformationDto dto = new StudentInformationDto();
        dto.setFirstName("Abcd");
        dto.setLastName("Xyz");
        dto.setEmail(email);
        dto.setCollegeName("HIJ College");
        dto.setDepartName("CSE");
        dto.setSemester(Integer.toString(5));
        dto.setPassingYear(Integer.toString(2025));
        dto.setIsGraduated(false);
        dto.setIsAdmitted(true);
        dto.setIsDiscontinued(false);
        dto.setReasonOfDiscontinue("N/A");
        dto.setBatches(new HashSet<>());
        dto.setCourses(new HashSet<>());
        return dto;
    }

    @Test
    void createStudent() {
        StudentInformationDto dto = buildStudent("create@xyz.com");
        StudentInformationDto saved = studentInformationService.createStudent(dto);

        assertNotNull(saved);
        assertEquals("create@xyz.com", saved.getEmail());
        assertEquals("Abcd", saved.getFirstName());
    }

    @Test
    void updateStudent() {
        // First create student
        StudentInformationDto dto = buildStudent("update@xyz.com");
        studentInformationService.createStudent(dto);

        // Update student details
        dto.setFirstName("UpdatedName");
        dto.setLastName("UpdatedLast");
        dto.setCollegeName("Updated College");

        StudentInformationDto updated = studentInformationService.updateStudent("update@xyz.com", dto);

        assertNotNull(updated);
        assertEquals("UpdatedName", updated.getFirstName());
        assertEquals("UpdatedLast", updated.getLastName());
        assertEquals("Updated College", updated.getCollegeName());
    }

    @Test
    void deleteStudent() {
        // First create student
        StudentInformationDto dto = buildStudent("delete@xyz.com");
        studentInformationService.createStudent(dto);

        String response = studentInformationService.deleteStudent("delete@xyz.com");
        assertTrue(response.contains("deleted successfully"));
    }

    @Test
    void getStudentByEmail() {
        // First create student
        StudentInformationDto dto = buildStudent("get@xyz.com");
        studentInformationService.createStudent(dto);

        StudentInformationDto fetched = studentInformationService.getStudentByEmail("get@xyz.com");

        assertNotNull(fetched);
        assertEquals("get@xyz.com", fetched.getEmail());
    }

    @Test
    void getAllStudents() {
        StudentInformationDto dto1 = buildStudent("all1@xyz.com");
        StudentInformationDto dto2 = buildStudent("all2@xyz.com");

        studentInformationService.createStudent(dto1);
        studentInformationService.createStudent(dto2);

        List<StudentInformationDto> students = studentInformationService.getAllStudents();

        assertNotNull(students);
        assertTrue(students.size() >= 2); // At least 2 students must exist
    }
}
