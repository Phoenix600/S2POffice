package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_information")
@Schema(description = "Entity representing detailed information about a student")
public class StudentInformation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier for the student", example = "e4eaaaf2-d142-11e1-b3e4-080027620cdd")
    private UUID studentInformationId;

    @Schema(description = "Student's first name", example = "John")
    private String firstName;

    @Schema(description = "Student's last name", example = "Doe")
    private String lastName;

    @Email(message = "Enter Valid Email")
    @Schema(description = "Student's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Name of the college the student is enrolled in", example = "JD College")
    private String collegeName;

    @Schema(description = "Department name", example = "Computer Science")
    private String departName;

    @Schema(description = "Current semester of the student", example = "6th")
    private String semester;

    @Schema(description = "Passing year of the student", example = "2025")
    private String passingYear;

    @Schema(description = "Indicates if the student has graduated")
    @Column(nullable = true)
    private Boolean isGraduated;

    @Schema(description = "Indicates if the student is admitted")
    private Boolean isAdmitted;

    @Schema(description = "Indicates if the student has discontinued")
    private Boolean isDiscontinued;

    @Schema(description = "Reason for discontinuation, if any", example = "Personal reasons")
    private String reasonOfDiscontinue;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "enquiry_id", referencedColumnName = "enquiryId")
    @Schema(description = "Linked enquiry entity for the student")
    private Enquiry enquiry;

    @ManyToMany
    @JoinTable(
            name = "student_batches",
            joinColumns = @JoinColumn(name = "student_information_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id")
    )
    @Schema(description = "Batches assigned to the student")
    private Set<Batch> batches = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_information_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Schema(description = "Courses enrolled by the student")
    private Set<Course> courses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_information_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Schema(description = "Course fee details for the student")
    private Set<CourseFees> courseFeesSet = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "course_fee_structure_id", referencedColumnName = "courseFeeStructureId")
    @Schema(description = "Associated course fee structure for the student")
    private CourseFeeStructure courseFeeStructure;
}
