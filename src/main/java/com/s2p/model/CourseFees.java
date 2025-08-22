package com.s2p.model;

import com.s2p.master.AcademicYear;
import com.s2p.master.model.AcademicYear;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * File Name: CourseFees.java
 * Entity: CourseFees
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFees extends BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID courseFeesID;

	private Long transactionId;

	private Double courseFees;

	@Embedded
	private CourseFeeStructure feeStructure;

	@ManyToOne
	@JoinColumn(name = "academic_year_id", nullable = false)
	private AcademicYear academicYear;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

}