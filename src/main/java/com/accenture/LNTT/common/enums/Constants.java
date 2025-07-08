package com.accenture.LNTT.common.enums;

public enum Constants {
    VALIDATION_FAILED("Validation Failed for some parameters"),
    LOGIN_SUCCESSFULLY("Login successful"),
    INVALID_CREDENTIALS("Invalid email or password"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    OPERATION_SUCCESSFULLY("Operation Successful"),
    RESOURCE_WITH_GIVEN_ID_NOT_FOUND("Resource with given id not found"),
    OPERATION_FAILED("Operation FAILED");

    Constants(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return this.value;
    }
}
