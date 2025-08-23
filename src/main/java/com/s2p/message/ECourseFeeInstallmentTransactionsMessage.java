package com.s2p.message;

/**
 * Messages related to Course Fee Installment Transactions.
 */
public enum ECourseFeeInstallmentTransactionsMessage implements IMessage {

    // --- CRUD ---
    CREATED_SUCCESS("Course fee installment transaction created successfully"),
    CREATED_FAILURE("Failed to create course fee installment transaction"),
    UPDATED_SUCCESS("Course fee installment transaction updated successfully"),
    UPDATED_FAILURE("Failed to update course fee installment transaction"),
    DELETED_SUCCESS("Course fee installment transaction deleted successfully"),
    DELETED_FAILURE("Failed to delete course fee installment transaction"),

    // --- Retrieval ---
    FOUND("Course fee installment transaction found successfully"),
    NOT_FOUND("Course fee installment transaction not found"),
    LIST_FETCHED("Course fee installment transactions retrieved successfully"),
    LIST_EMPTY("No course fee installment transactions available"),

    // --- Validation ---
    INVALID_AMOUNT("Invalid installment amount"),
    AMOUNT_REQUIRED("Installment amount is required"),
    TRANSACTION_ID_REQUIRED("Transaction ID is required"),
    DUPLICATE_TRANSACTION("Duplicate installment transaction already exists");

    private final String message;

    ECourseFeeInstallmentTransactionsMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
