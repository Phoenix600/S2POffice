package com.s2p.junitTesting;

import com.s2p.dto.CourseFeeDto;
import com.s2p.services.ICourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ICourseFeeServiceJUnitTest {

    @Autowired
    ICourseService iCourseService;

    @Test
    void createCourseFee()
    {
        CourseFeeDto courseFeeDto = new CourseFeeDto();

        courseFeeDto.setCourseFees(30000.00);
        courseFeeDto.
    }

    @Test
    void getFeesByCourseName() {
    }

    @Test
    void getFeesByAcademicYear() {
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void updateCourseFeeByCourseName() {
    }

    @Test
    void deleteCourseFeesByCourseName() {
    }
}