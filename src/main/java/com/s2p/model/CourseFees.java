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
	@Schema(description = "Unique identifier for the course fees record", example = "123e4567-e89b-12d3-a456-426614174000")
	private UUID courseFeesID;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "courseId", nullable = false)
	@Schema(description = "Course associated with these fees")
	private Course course;

	@Column(nullable = false)
	@Schema(description = "Fees amount for the course", example = "50000.0")
	private Double courseFees;

	@ManyToOne
	@JoinColumn(name = "academic_year_id", nullable = false)
	@Schema(description = "Academic year for which these fees are applicable")
	private AcademicYear academicYear;

	private Double amountExpected;

	private LocalDate paymentDate;

	private Double amountPaid;

	private LocalDate dueDate;
}