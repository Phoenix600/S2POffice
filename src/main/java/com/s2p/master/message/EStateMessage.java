package com.s2p.master.message;

/**
 * Messages related to State operations.
 */
public enum EStateMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("State created successfully"),
    CREATED_FAILURE("Failed to create state"),
    UPDATED_SUCCESS("State updated successfully"),
    UPDATED_FAILURE("Failed to update state"),
    DELETED_SUCCESS("State deleted successfully"),
    DELETED_FAILURE("Failed to delete state"),

    // --- Retrieval ---
    FOUND("State found successfully"),
    NOT_FOUND("State not found"),
    LIST_FETCHED("State list retrieved successfully"),
    LIST_EMPTY("No states available"),

    // --- Validation ---
    NAME_REQUIRED("State name is required"),
    NAME_EXISTS("State with this name already exists"),
    INVALID_NAME("Invalid state name provided"),

    // --- Associations (City) ---
    CITY_ASSIGNED("City assigned to state successfully"),
    CITY_ALREADY_ASSIGNED("City is already assigned to this state"),
    CITY_REMOVED("City removed from state successfully"),
    CITY_NOT_FOUND("City not found in this state");

    private final String message;

    EStateMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
