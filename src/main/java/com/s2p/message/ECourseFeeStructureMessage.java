package com.s2p.message;

/**
 * Messages related to Course Fee Structure validation.
 */
public enum ECourseFeeStructureMessage implements IMessage {

    // --- Validation ---
    AMOUNT_REQUIRED("Course fee amount is required"),
    INVALID_AMOUNT("Course fee amount must be greater than zero"),

    // Future extensibility (if fields are added later like installments, currency etc.)
    INSTALLMENTS_REQUIRED("Number of installments is required"),
    INVALID_INSTALLMENTS("Invalid number of installments"),
    DUE_DATE_REQUIRED("Due date is required for fee installment"),
    INVALID_DUE_DATE("Invalid due date provided");

    private final String message;

    ECourseFeeStructureMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
