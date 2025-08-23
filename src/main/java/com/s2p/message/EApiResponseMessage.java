package com.s2p.message;

/**
 * Messages related to generic API responses.
 */
public enum EApiResponseMessage implements IMessage {

    // --- Generic Responses ---
    SUCCESS("Request processed successfully"),
    FAILURE("Request processing failed"),

    // --- Data Handling ---
    DATA_FOUND("Data retrieved successfully"),
    DATA_NOT_FOUND("No data available"),
    DATA_SAVED("Data saved successfully"),
    DATA_UPDATED("Data updated successfully"),
    DATA_DELETED("Data deleted successfully"),

    // --- Errors ---
    INTERNAL_ERROR("An internal server error occurred"),
    BAD_REQUEST("Invalid request"),
    UNAUTHORIZED("Unauthorized access"),
    FORBIDDEN("Access forbidden"),
    TIMEOUT("Request timed out"),

    // --- Validation ---
    INVALID_INPUT("Invalid input provided"),
    MISSING_REQUIRED_FIELD("Missing required field"),
    CONSTRAINT_VIOLATION("Data violates constraints");

    private final String message;

    EApiResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
