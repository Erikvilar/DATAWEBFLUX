package com.ltadcrm.ltadcrm.domain.Enums;

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
