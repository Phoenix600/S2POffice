package com.s2p.model;

import com.s2p.master.model.AcademicYear;
import jakarta.persistence.*;
import lombok.*;

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

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "courseId", nullable = false)
	private Course course;

	@Column(nullable = false)
	private Double courseFees;

	@ManyToOne
	@JoinColumn(name = "academic_year_id", nullable = false)
	private AcademicYear academicYear;

}