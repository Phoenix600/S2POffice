package com.s2p.message;

/**
 * Messages related to Student operations.
 */
public enum EStudentUsersMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Student created successfully"),
    CREATED_FAILURE("Failed to create Student"),
    UPDATED_SUCCESS("Student updated successfully"),
    UPDATED_FAILURE("Failed to update Student"),
    DELETED_SUCCESS("Student deleted successfully"),
    DELETED_FAILURE("Failed to delete Student"),

    // --- Retrieval ---
    FOUND("Student found successfully"),
    NOT_FOUND("Student not found"),
    LIST_FETCHED("Student list retrieved successfully"),
    LIST_EMPTY("No Students available"),

    // --- Validation ---
    USERNAME_EXISTS("Student username already exists"),
    EMAIL_EXISTS("Student email already exists"),

    // --- Authentication ---
    LOGIN_SUCCESS("Student login successful"),
    LOGIN_FAILED("Invalid Student credentials"),
    UNAUTHORIZED("Student is not authorized for this action");

    private final String message;

    EStudentUsersMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
