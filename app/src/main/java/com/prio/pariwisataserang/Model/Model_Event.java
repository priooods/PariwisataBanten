package com.prio.pariwisataserang.Model;

public class Model_Event {

    private String title;
    private String date;
    private String tempat;
    private String kota;
    private String image;

    public Model_Event() {
    }

    public Model_Event(String title, String date, String tempat, String kota,String image) {
        this.title = title;
        this.date = date;
        this.tempat = tempat;
        this.kota = kota;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
