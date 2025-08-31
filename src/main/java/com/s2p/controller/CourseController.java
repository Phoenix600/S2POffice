package com.s2p.controller;

import com.s2p.dto.CourseDto;
import com.s2p.services.impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController
{
    @Autowired
    CourseService courseService;

    @GetMapping("/search")
    public List<CourseDto> searchCourses(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Byte duration
    ) {
        return courseService.searchCourses(courseName, description, duration);
    }
}
