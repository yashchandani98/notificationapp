package com.bitgoassesment.notificationApp.entity;

import lombok.Getter;

@Getter
public enum NotificationStatus {
    SENT("Sent"),
    OUTSTANDING("Outstanding"),
    DELETED("Deleted"),
    FAILED("Failed");

    // Method to get the custom string value of the enum
    private final String stringValue;

    // Constructor to assign custom string values to enum constants
    NotificationStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    // Static method to get the enum constant from a string value
    public static NotificationStatus fromString(String value) {
        for (NotificationStatus status : NotificationStatus.values()) {
            if (status.getStringValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value); // If no match is found
    }
}
