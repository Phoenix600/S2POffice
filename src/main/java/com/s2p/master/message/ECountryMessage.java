package com.s2p.master.message;

/**
 * Messages related to Country operations.
 */
public enum ECountryMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Country created successfully"),
    CREATED_FAILURE("Failed to create country"),
    UPDATED_SUCCESS("Country updated successfully"),
    UPDATED_FAILURE("Failed to update country"),
    DELETED_SUCCESS("Country deleted successfully"),
    DELETED_FAILURE("Failed to delete country"),

    // --- Retrieval ---
    FOUND("Country found successfully"),
    NOT_FOUND("Country not found"),
    LIST_FETCHED("Country list retrieved successfully"),
    LIST_EMPTY("No countries available"),

    // --- Validation ---
    NAME_REQUIRED("Country name is required"),
    NAME_EXISTS("Country with this name already exists"),
    INVALID_NAME("Invalid country name provided"),

    // --- Associations (State) ---
    STATE_ASSIGNED("State assigned to country successfully"),
    STATE_ALREADY_ASSIGNED("State is already assigned to this country"),
    STATE_REMOVED("State removed from country successfully"),
    STATE_NOT_FOUND("State not found in this country");

    private final String message;

    ECountryMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
