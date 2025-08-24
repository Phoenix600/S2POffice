package com.s2p.master.message;



/**
 * Messages related to City operations.
 */
public enum ECityMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("City created successfully"),
    CREATED_FAILURE("Failed to create city"),
    UPDATED_SUCCESS("City updated successfully"),
    UPDATED_FAILURE("Failed to update city"),
    DELETED_SUCCESS("City deleted successfully"),
    DELETED_FAILURE("Failed to delete city"),

    // --- Retrieval ---
    FOUND("City found successfully"),
    NOT_FOUND("City not found"),
    LIST_FETCHED("City list retrieved successfully"),
    LIST_EMPTY("No cities available"),

    // --- Validation ---
    NAME_REQUIRED("City name is required"),
    NAME_EXISTS("City with this name already exists"),
    INVALID_NAME("Invalid city name provided");

    private final String message;

    ECityMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
