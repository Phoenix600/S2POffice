package com.s2p.message;

/**
 * Messages related to Course Fees operations.
 */
public enum ECourseFeesMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Course fees record created successfully"),
    CREATED_FAILURE("Failed to create course fees record"),
    UPDATED_SUCCESS("Course fees record updated successfully"),
    UPDATED_FAILURE("Failed to update course fees record"),
    DELETED_SUCCESS("Course fees record deleted successfully"),
    DELETED_FAILURE("Failed to delete course fees record"),

    // --- Retrieval ---
    FOUND("Course fees record found successfully"),
    NOT_FOUND("Course fees record not found"),
    LIST_FETCHED("Course fees list retrieved successfully"),
    LIST_EMPTY("No course fees available"),

    // --- Validation ---
    INVALID_AMOUNT("Invalid course fees amount"),
    AMOUNT_REQUIRED("Course fees amount is required"),
    ALREADY_EXISTS("Course fees already defined for this course and academic year"),

    // --- Associations ---
    ACADEMIC_YEAR_ASSIGNED("Academic year linked to course fees successfully"),
    ACADEMIC_YEAR_NOT_FOUND("Academic year not found for course fees"),
    COURSE_ASSIGNED("Course linked to course fees successfully"),
    COURSE_NOT_FOUND("Course not found for course fees");

    private final String message;

    ECourseFeesMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
