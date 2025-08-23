package com.s2p.message;

/**
 * Messages related to Teacher operations.
 */
public enum ETeacherUsersMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Teacher created successfully"),
    CREATED_FAILURE("Failed to create Teacher"),
    UPDATED_SUCCESS("Teacher updated successfully"),
    UPDATED_FAILURE("Failed to update Teacher"),
    DELETED_SUCCESS("Teacher deleted successfully"),
    DELETED_FAILURE("Failed to delete Teacher"),

    // --- Retrieval ---
    FOUND("Teacher found successfully"),
    NOT_FOUND("Teacher not found"),
    LIST_FETCHED("Teacher list retrieved successfully"),
    LIST_EMPTY("No Teachers available"),

    // --- Validation ---
    USERNAME_EXISTS("Teacher username already exists"),
    EMAIL_EXISTS("Teacher email already exists"),

    // --- Authentication ---
    LOGIN_SUCCESS("Teacher login successful"),
    LOGIN_FAILED("Invalid Teacher credentials"),
    UNAUTHORIZED("Teacher is not authorized for this action");

    private final String message;

    ETeacherUsersMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
