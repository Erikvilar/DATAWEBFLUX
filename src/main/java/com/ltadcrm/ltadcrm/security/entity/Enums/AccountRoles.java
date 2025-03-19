package com.ltadcrm.ltadcrm.security.entity.Enums;

public enum AccountRoles {

    VISITER("visiter"), // very low access
    USER("user"), // mid leve access
    SECRETARY("secretary"), //special access
    MANAGER("manager"), // high access
    ADMIN("admin"); // full accesss

    private String role;

    AccountRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
