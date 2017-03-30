package com.tikal.web.entities;

/**
 * Created by pavel_sopher on 26/03/2017.
 */
public class WebRole {
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "WebRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
