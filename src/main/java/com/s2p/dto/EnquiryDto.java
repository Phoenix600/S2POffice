package com.s2p.dto;

import com.s2p.model.Course;
import com.s2p.model.StudentInformation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnquiryDto {
    private UUID enquiryId;

    private LocalDate enquiryDate;

    private StudentInformation studentInformation;

    private Set<Course> courseSet = new HashSet<>();
}
