package com.s2p.message;

/**
 * Messages related to Super Admin operations.
 */
public enum ESuperAdminUsersMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Super Admin created successfully"),
    CREATED_FAILURE("Failed to create Super Admin"),
    UPDATED_SUCCESS("Super Admin updated successfully"),
    UPDATED_FAILURE("Failed to update Super Admin"),
    DELETED_SUCCESS("Super Admin deleted successfully"),
    DELETED_FAILURE("Failed to delete Super Admin"),

    // --- Retrieval ---
    FOUND("Super Admin found successfully"),
    NOT_FOUND("Super Admin not found"),
    LIST_FETCHED("Super Admin list retrieved successfully"),
    LIST_EMPTY("No Super Admins available"),

    // --- Validation ---
    USERNAME_EXISTS("Super Admin username already exists"),
    EMAIL_EXISTS("Super Admin email already exists"),

    // --- Authentication ---
    LOGIN_SUCCESS("Super Admin login successful"),
    LOGIN_FAILED("Invalid Super Admin credentials"),
    UNAUTHORIZED("Super Admin is not authorized for this action");

    private final String message;

    ESuperAdminUsersMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
