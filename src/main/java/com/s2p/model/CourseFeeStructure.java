package com.s2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseFeeStructure
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseFeeStructureId;

    private Double downPayment;
    private Double remainingAmount;
    private Boolean isDiscountGiven;
    private Float isDiscountFactor;
    private Byte nInstallments;
    private Byte remainingInstallments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_fees_id", referencedColumnName = "courseFeesID")
    private CourseFees courseFees;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_user_id", referencedColumnName = "studentUserId")
    private StudentUsers studentUsers;

}