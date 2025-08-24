package com.s2p.master.message;

/**
 * Messages related to Department operations.
 */
public enum EDepartmentMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Department created successfully"),
    CREATED_FAILURE("Failed to create department"),
    UPDATED_SUCCESS("Department updated successfully"),
    UPDATED_FAILURE("Failed to update department"),
    DELETED_SUCCESS("Department deleted successfully"),
    DELETED_FAILURE("Failed to delete department"),

    // --- Retrieval ---
    FOUND("Department found successfully"),
    NOT_FOUND("Department not found"),
    LIST_FETCHED("Department list retrieved successfully"),
    LIST_EMPTY("No departments available"),

    // --- Validation ---
    NAME_REQUIRED("Department name is required"),
    NAME_EXISTS("Department with this name already exists"),
    INVALID_NAME("Invalid department name provided"),

    // --- Associations (College) ---
    COLLEGE_ASSIGNED("College assigned to department successfully"),
    COLLEGE_ALREADY_ASSIGNED("College is already assigned to this department"),
    COLLEGE_REMOVED("College removed from department successfully"),
    COLLEGE_NOT_FOUND("College not found in this department");

    private final String message;

    EDepartmentMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
