package com.s2p.message;

/**
 * Messages related to Role operations.
 */
public enum ERoleMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Role created successfully"),
    CREATED_FAILURE("Failed to create role"),
    UPDATED_SUCCESS("Role updated successfully"),
    UPDATED_FAILURE("Failed to update role"),
    DELETED_SUCCESS("Role deleted successfully"),
    DELETED_FAILURE("Failed to delete role"),

    // --- Retrieval ---
    FOUND("Role found successfully"),
    NOT_FOUND("Role not found"),
    LIST_FETCHED("Role list retrieved successfully"),
    LIST_EMPTY("No roles available"),

    // --- Validation ---
    NAME_REQUIRED("Role name is required"),
    NAME_EXISTS("Role name already exists"),
    INVALID_ROLE("Invalid role specified"),

    // --- Associations ---
    USER_ASSIGNED("User assigned to role successfully"),
    USER_ALREADY_ASSIGNED("User is already assigned to this role"),
    USER_REMOVED("User removed from role successfully"),
    USER_NOT_FOUND("User not found for this role");

    private final String message;

    ERoleMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
