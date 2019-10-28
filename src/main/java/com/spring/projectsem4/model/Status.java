package com.spring.projectsem4.model;

public enum Status {

    ACTIVE(1),
    DEACTIVE(2);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status formValue(int value) {
        Status status = ACTIVE;
        switch (value) {
            case 0:
                status = ACTIVE;
                break;
            case 9:
                status = DEACTIVE;
            default:
                break;
        }
        return status;
    }
}
