package com.s2p.repository.specifications;

import com.s2p.model.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    public static Specification<Course> hasCourseName(String courseName) {
        return (root, query, cb) ->
                courseName == null ? null : cb.like(cb.lower(root.get("courseName")), "%" + courseName.toLowerCase() + "%");
    }

    public static Specification<Course> hasDescription(String description) {
        return (root, query, cb) ->
                description == null ? null : cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Course> hasDuration(Byte duration) {
        return (root, query, cb) ->
                duration == null ? null : cb.equal(root.get("courseDurationInMonths"), duration);
    }
}
