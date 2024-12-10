package com.ltadcrm.ltadcrm.security.entity.Enums;

public enum AccountRoles {
    ADMIN("admin"),
    USER("user");

  

    private String role;

        AccountRoles(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }

}
