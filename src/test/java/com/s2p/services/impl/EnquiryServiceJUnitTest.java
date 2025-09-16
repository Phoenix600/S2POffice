package com.s2p.services.impl;

import com.s2p.dto.EnquiryDto;
import com.s2p.exceptions.ResourceNotFoundException;
import com.s2p.model.Course;
import com.s2p.model.Enquiry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnquiryServiceJUnitTest
{
    @Autowired
    EnquiryService enquiryService;

    @Autowired
    com.s2p.service.impl.StudentInformationService studentInformationService;

    @Autowired
    CourseService courseService;

    @Test
    void test_create_enquiry_without_student_information_failure()
    {

    }

  
}