package com.s2p.message;

/**
 * Messages related to Admin operations.
 */
public enum EAdminUsersMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Admin created successfully"),
    CREATED_FAILURE("Failed to create Admin"),
    UPDATED_SUCCESS("Admin updated successfully"),
    UPDATED_FAILURE("Failed to update Admin"),
    DELETED_SUCCESS("Admin deleted successfully"),
    DELETED_FAILURE("Failed to delete Admin"),

    // --- Retrieval ---
    FOUND("Admin found successfully"),
    NOT_FOUND("Admin not found"),
    LIST_FETCHED("Admin list retrieved successfully"),
    LIST_EMPTY("No Admins available"),

    // --- Validation ---
    USERNAME_EXISTS("Admin username already exists"),
    EMAIL_EXISTS("Admin email already exists"),
    EMAIL_SHOULD_NOT_BE_EMPTY("Admin email field should not be empty"),

    // --- Authentication ---
    LOGIN_SUCCESS("Admin login successful"),
    LOGIN_FAILED("Invalid Admin credentials"),
    UNAUTHORIZED("Admin is not authorized for this action");

    private final String message;

    EAdminUsersMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
