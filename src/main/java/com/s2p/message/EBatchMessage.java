package com.s2p.message;

/**
 * Messages related to Batch operations.
 */
public enum EBatchMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Batch created successfully"),
    CREATED_FAILURE("Failed to create Batch"),
    UPDATED_SUCCESS("Batch updated successfully"),
    UPDATED_FAILURE("Failed to update Batch"),
    DELETED_SUCCESS("Batch deleted successfully"),
    DELETED_FAILURE("Failed to delete Batch"),

    // --- Retrieval ---
    FOUND("Batch found successfully"),
    NOT_FOUND("Batch not found"),
    LIST_FETCHED("Batch list retrieved successfully"),
    LIST_EMPTY("No Batches available"),

    // --- Validation ---
    NAME_REQUIRED("Batch name is required"),
    START_TIME_REQUIRED("Batch start time is required"),
    END_TIME_REQUIRED("Batch end time is required"),
    INVALID_TIME_RANGE("End time must be after start time"),
    ALREADY_EXISTS("Batch already exists"),

    // --- Associations: Course ---
    COURSE_ASSIGNED("Course assigned to Batch successfully"),
    COURSE_ALREADY_ASSIGNED("Course is already assigned to this Batch"),
    COURSE_REMOVED("Course removed from Batch successfully"),
    COURSE_NOT_FOUND("Course not found in this Batch"),

    // --- Associations: Student ---
    STUDENT_ASSIGNED("Student assigned to Batch successfully"),
    STUDENT_ALREADY_ASSIGNED("Student is already assigned to this Batch"),
    STUDENT_REMOVED("Student removed from Batch successfully"),
    STUDENT_NOT_FOUND("Student not found in this Batch");

    private final String message;

    EBatchMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
