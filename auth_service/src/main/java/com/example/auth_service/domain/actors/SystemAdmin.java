package com.example.auth_service.domain.actors;

public class SystemAdmin {
    private Account account;
    
    public boolean blockUser() { 
        return true;
    }

    // ** need to resolve this 
    public boolean addNewCategory() { 
        return true;
    }

    public boolean modifyCategory() { 
        return true;
    }
}
