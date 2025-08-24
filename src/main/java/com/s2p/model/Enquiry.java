package com.s2p.model;

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

/**
 * File Name: Enquiry.java
 * Entity: Enquiry
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Enquiry
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID enquiryId;

    @CreatedDate
    private LocalDate enquiryDate;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private StudentInformation studentInformation;

    @ManyToMany()
    @JoinTable(
            name = "enquiry_course",
            joinColumns = {@JoinColumn(name = " enquiry_id", referencedColumnName = "enquiryId")},
            inverseJoinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "courseId")}

    )
    private Set<Course> courseSet = new HashSet<>();

}

