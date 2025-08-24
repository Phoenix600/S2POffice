package com.s2p.master.message;


/**
 * Messages related to Branch operations.
 */
public enum EBranchMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Branch created successfully"),
    CREATED_FAILURE("Failed to create branch"),
    UPDATED_SUCCESS("Branch updated successfully"),
    UPDATED_FAILURE("Failed to update branch"),
    DELETED_SUCCESS("Branch deleted successfully"),
    DELETED_FAILURE("Failed to delete branch"),

    // --- Retrieval ---
    FOUND("Branch found successfully"),
    NOT_FOUND("Branch not found"),
    LIST_FETCHED("Branch list retrieved successfully"),
    LIST_EMPTY("No branches available"),

    // --- Validation ---
    NAME_REQUIRED("Branch name is required"),
    NAME_EXISTS("Branch with this name already exists"),
    INVALID_NAME("Invalid branch name provided");

    private final String message;

    EBranchMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
