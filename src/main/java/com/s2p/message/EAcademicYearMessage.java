package com.s2p.message;

/**
 * Messages related to Academic Year operations.
 */
public enum EAcademicYearMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Academic year created successfully"),
    CREATED_FAILURE("Failed to create academic year"),
    UPDATED_SUCCESS("Academic year updated successfully"),
    UPDATED_FAILURE("Failed to update academic year"),
    DELETED_SUCCESS("Academic year deleted successfully"),
    DELETED_FAILURE("Failed to delete academic year"),

    // --- Retrieval ---
    FOUND("Academic year found successfully"),
    NOT_FOUND("Academic year not found"),
    LIST_FETCHED("Academic year list retrieved successfully"),
    LIST_EMPTY("No academic years available"),

    // --- Validation ---
    YEAR_REQUIRED("Academic year is required"),
    INVALID_YEAR("Invalid academic year format"),
    ALREADY_EXISTS("Academic year already exists"),

    // --- Associations ---
    COURSE_FEES_ASSIGNED("Course fees linked to academic year successfully"),
    COURSE_FEES_NOT_FOUND("Course fees not found for this academic year");

    private final String message;

    EAcademicYearMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
