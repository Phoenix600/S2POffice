package com.s2p.message;

/**
 * Messages related to Enquiry operations.
 */
public enum EEnquiryMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Enquiry created successfully"),
    CREATED_FAILURE("Failed to create Enquiry"),
    UPDATED_SUCCESS("Enquiry updated successfully"),
    UPDATED_FAILURE("Failed to update Enquiry"),
    DELETED_SUCCESS("Enquiry deleted successfully"),
    DELETED_FAILURE("Failed to delete Enquiry"),

    // --- Retrieval ---
    FOUND("Enquiry found successfully"),
    NOT_FOUND("Enquiry not found"),
    LIST_FETCHED("Enquiry list retrieved successfully"),
    LIST_EMPTY("No Enquiries available"),

    // --- Validation ---
    DATE_REQUIRED("Enquiry date is required"),
    INVALID_DATE("Invalid enquiry date"),
    ALREADY_EXISTS("Duplicate enquiry already exists"),

    // --- Associations ---
    STUDENT_LINKED("Student linked to Enquiry successfully"),
    STUDENT_ALREADY_LINKED("Student is already linked to this Enquiry"),
    STUDENT_REMOVED("Student removed from Enquiry successfully"),
    STUDENT_NOT_FOUND("Student not found for this Enquiry");

    private final String message;

    EEnquiryMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
