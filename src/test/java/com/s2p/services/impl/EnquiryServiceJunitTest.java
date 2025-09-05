package com.s2p.services.impl;

import com.s2p.dto.CourseDto;
import com.s2p.dto.EnquiryDto;
import com.s2p.model.Course;
import com.s2p.model.Enquiry;
import com.s2p.util.CourseUtility;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit5.AllureJunit5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(AllureJunit5.class)
@Epic("Master Data Module")
@Feature("Country Service")
@Slf4j
@SpringBootTest
class EnquiryServiceJunitTest
{
    @Autowired
    EnquiryService enquiryService;

    @Autowired
    StudentInformationService studentInformationService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseUtility courseUtility;

    @Test
    void test_create_enquiry_without_student_information_failure()
    {

    }



}