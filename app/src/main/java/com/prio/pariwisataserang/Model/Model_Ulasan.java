package com.prio.pariwisataserang.Model;

import com.google.firebase.database.ServerValue;

public class Model_Ulasan {

    private String ulasan, username,uid,photo;
    private Object timestamp;
    private double banyakrating;

    public Model_Ulasan(String ulasan, String username, String uid, String photo, double banyakrating) {
        this.ulasan = ulasan;
        this.username = username;
        this.uid = uid;
        this.photo = photo;
        this.banyakrating = banyakrating;

        this.timestamp = ServerValue.TIMESTAMP;
    }

    public Model_Ulasan() {
    }

    public double getBanyakrating() {
        return banyakrating;
    }

    public void setBanyakrating(double banyakrating) {
        this.banyakrating = banyakrating;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
