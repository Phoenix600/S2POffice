package com.s2p.message;

/**
 * Messages related to Admission operations.
 */
public enum EAdmissionMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Admission created successfully"),
    CREATED_FAILURE("Failed to create Admission"),
    UPDATED_SUCCESS("Admission updated successfully"),
    UPDATED_FAILURE("Failed to update Admission"),
    DELETED_SUCCESS("Admission deleted successfully"),
    DELETED_FAILURE("Failed to delete Admission"),

    // --- Retrieval ---
    FOUND("Admission found successfully"),
    NOT_FOUND("Admission not found"),
    LIST_FETCHED("Admission list retrieved successfully"),
    LIST_EMPTY("No Admissions available"),

    // --- Validation ---
    DATE_REQUIRED("Admission date is required"),
    INVALID_DATE("Invalid admission date"),
    ALREADY_EXISTS("Admission already exists for this student"),

    // --- Associations ---
    STUDENT_LINKED("Student linked to Admission successfully"),
    STUDENT_ALREADY_LINKED("Student is already linked to this Admission"),
    STUDENT_REMOVED("Student removed from Admission successfully"),
    STUDENT_NOT_FOUND("Student not found for this Admission"),

    COURSE_LINKED("Course linked to Admission successfully"),
    COURSE_ALREADY_LINKED("Course is already linked to this Admission"),
    COURSE_REMOVED("Course removed from Admission successfully"),
    COURSE_NOT_FOUND("Course not found for this Admission");

    private final String message;

    EAdmissionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
