package com.s2p.model;

import com.s2p.master.model.AcademicYear;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * File Name: CourseFees.java
 * Entity: CourseFees
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description: Represents the fees for a course in a specific academic year.
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

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "courseId", nullable = false)
	private Course course;

	@Column(nullable = false)
	private Double courseFees;

	@ManyToOne
	@JoinColumn(name = "academic_year_id", nullable = false)
	private AcademicYear academicYear;

	@Column(nullable = false)
	private Double amountExpected;

	@Column(nullable = false)
	private LocalDate paymentDate;

	@Column(nullable = false)
	private Double amountPaid;

	@Column(nullable = false)
	private LocalDate dueDate;
}