package com.example.auth_service.service.dto;

public class ChangePasswordDTO {
    
    private String currentPassword;

    private String newPassword;
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 50;

    public ChangePasswordDTO() {}

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean checkValidity() {
        if (currentPassword.length() < MIN_LENGTH || currentPassword.length() > MAX_LENGTH ) return false;
        return true;
    }
    
}
