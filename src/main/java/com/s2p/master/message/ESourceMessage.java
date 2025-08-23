package com.s2p.master.message;

/**
 * Messages related to Source operations.
 */
public enum ESourceMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Source created successfully"),
    CREATED_FAILURE("Failed to create source"),
    UPDATED_SUCCESS("Source updated successfully"),
    UPDATED_FAILURE("Failed to update source"),
    DELETED_SUCCESS("Source deleted successfully"),
    DELETED_FAILURE("Failed to delete source"),

    // --- Retrieval ---
    FOUND("Source found successfully"),
    NOT_FOUND("Source not found"),
    LIST_FETCHED("Source list retrieved successfully"),
    LIST_EMPTY("No sources available"),

    // --- Validation ---
    NAME_REQUIRED("Source name is required"),
    NAME_EXISTS("Source with this name already exists"),
    INVALID_NAME("Invalid source name provided"),

    // --- Business Context ---
    ASSIGNED_TO_ENQUIRY("Source assigned to enquiry successfully"),
    UNASSIGNED_FROM_ENQUIRY("Source unassigned from enquiry successfully"),
    ASSIGNMENT_FAILED("Failed to assign source to enquiry");

    private final String message;

    ESourceMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
