package com.prio.pariwisataserang.Model;

public class Model_Gambar {
    private String photo, photoId;

    public Model_Gambar(String photo, String photoId) {
        this.photo = photo;
        this.photoId = photoId;
    }

    public Model_Gambar() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
}
