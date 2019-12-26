package com.prio.pariwisataserang.Model;

public class Model_Radar {
    private String status, email;

    public Model_Radar() {
    }

    public Model_Radar(String status, String email) {
        this.status = status;
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
