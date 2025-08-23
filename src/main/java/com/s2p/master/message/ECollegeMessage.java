package com.s2p.master.message;



/**
 * Messages related to College operations.
 */
public enum ECollegeMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("College created successfully"),
    CREATED_FAILURE("Failed to create college"),
    UPDATED_SUCCESS("College updated successfully"),
    UPDATED_FAILURE("Failed to update college"),
    DELETED_SUCCESS("College deleted successfully"),
    DELETED_FAILURE("Failed to delete college"),

    // --- Retrieval ---
    FOUND("College found successfully"),
    NOT_FOUND("College not found"),
    LIST_FETCHED("College list retrieved successfully"),
    LIST_EMPTY("No colleges available"),

    // --- Validation ---
    NAME_REQUIRED("College name is required"),
    NAME_EXISTS("College with this name already exists"),
    INVALID_NAME("Invalid college name provided"),

    // --- Associations (Department) ---
    DEPARTMENT_ASSIGNED("Department assigned to college successfully"),
    DEPARTMENT_ALREADY_ASSIGNED("Department is already assigned to this college"),
    DEPARTMENT_REMOVED("Department removed from college successfully"),
    DEPARTMENT_NOT_FOUND("Department not found in this college");

    private final String message;

    ECollegeMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
