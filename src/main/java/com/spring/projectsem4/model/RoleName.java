package com.spring.projectsem4.model;

public enum RoleName {
    USER(1), ADMIN(0), CUSTOMER(2);

    private int value;

    RoleName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoleName formValue(int value) {
        RoleName roleName = USER;
        switch (value) {
            case 0:
                roleName = ADMIN;
                break;
            case 1:
                roleName = USER;
                break;
            case 9:
                roleName = CUSTOMER;
            default:
                break;
        }
        return roleName;
    }
}
