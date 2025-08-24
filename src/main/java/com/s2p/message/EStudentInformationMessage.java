package com.s2p.message;

/**
 * Messages related to Student Information operations.
 */
public enum EStudentInformationMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Student information saved successfully"),
    CREATED_FAILURE("Failed to save student information"),
    UPDATED_SUCCESS("Student information updated successfully"),
    UPDATED_FAILURE("Failed to update student information"),
    DELETED_SUCCESS("Student information deleted successfully"),
    DELETED_FAILURE("Failed to delete student information"),

    // --- Retrieval ---
    FOUND("Student information found successfully"),
    NOT_FOUND("Student information not found"),
    LIST_FETCHED("Student information list retrieved successfully"),
    LIST_EMPTY("No student information available"),

    // --- Validation ---
    EMAIL_REQUIRED("Student email is required"),
    INVALID_EMAIL("Invalid student email provided"),
    EMAIL_EXISTS("Student email already exists"),
    FIRST_NAME_REQUIRED("First name is required"),
    LAST_NAME_REQUIRED("Last name is required"),
    COLLEGE_NAME_REQUIRED("College name is required"),
    DEGREE_NAME_REQUIRED("Degree name is required"),
    SEMESTER_REQUIRED("Semester is required"),
    PASSING_YEAR_REQUIRED("Passing year is required"),

    // --- Associations: Batch ---
    BATCH_ASSIGNED("Batch assigned to student successfully"),
    BATCH_ALREADY_ASSIGNED("Batch already assigned to student"),
    BATCH_REMOVED("Batch removed from student successfully"),
    BATCH_NOT_FOUND("Batch not found for this student"),

    // --- Associations: Course ---
    COURSE_ASSIGNED("Course assigned to student successfully"),
    COURSE_ALREADY_ASSIGNED("Course already assigned to student"),
    COURSE_REMOVED("Course removed from student successfully"),
    COURSE_NOT_FOUND("Course not found for this student");

    private final String message;

    EStudentInformationMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
