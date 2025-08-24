package com.s2p.message;

/**
 * Base messages for all User operations.
 */
public enum EUserMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("User created successfully"),
    CREATED_FAILURE("Failed to create user"),
    UPDATED_SUCCESS("User updated successfully"),
    UPDATED_FAILURE("Failed to update user"),
    DELETED_SUCCESS("User deleted successfully"),
    DELETED_FAILURE("Failed to delete user"),

    // --- Retrieval ---
    FOUND("User found successfully"),
    NOT_FOUND("User not found"),
    LIST_FETCHED("User list retrieved successfully"),
    LIST_EMPTY("No users available"),

    // --- Validation ---
    USERNAME_EXISTS("Username already exists"),
    EMAIL_EXISTS("Email already exists"),
    INVALID_EMAIL("Invalid email address provided"),
    EMAIL_MISMATCH("Email and confirm email do not match"),
    PASSWORD_REQUIRED("Password is required"),
    PASSWORD_TOO_WEAK("Password is too weak"),
    PASSWORD_MISMATCH("Password and confirm password do not match"),

    // --- Authentication / Authorization ---
    LOGIN_SUCCESS("Login successful"),
    LOGIN_FAILED("Invalid username or password"),
    UNAUTHORIZED("User is not authorized to perform this action"),
    ROLE_ASSIGNED("Role assigned successfully"),
    ROLE_NOT_FOUND("Role not found for this user"),
    ACCOUNT_LOCKED("User account is locked"),
    ACCOUNT_DISABLED("User account is disabled"),
    PASSWORD_EXPIRED("Password has expired, reset required");

    private final String message;

    EUserMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
