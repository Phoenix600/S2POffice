package com.s2p.junitTesting;

import com.s2p.dto.CourseDto;
import com.s2p.services.ICourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class  CourseServiceJunit
{
    @Autowired
    ICourseService courseService;

    @Test
    void test_create_course_success()
    {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Java Full Stack Mastery");
        courseDto.setDescription("This is some funny description");
        courseDto.setCourseDurationInMonths((byte)12);

        CourseDto savedCourseDto =  courseService.createCourse(courseDto);
    }

    @Test
    void test_create_course_with_partial_fields_failure()
    {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("React UI Kingdom"); // Missing other mandatory fields

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.createCourse(courseDto);
        });

        // Verify
        String expectedMessage = "Required fields are missing";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void getCourseByName()
    {

    }

    @Test
    void getAllCourses() {
    }

    @Test
    void updateCourseByName() {
    }

    @Test
    void deleteCourseByName() {
    }

    @Test
    void searchCourses() {
    }
}
