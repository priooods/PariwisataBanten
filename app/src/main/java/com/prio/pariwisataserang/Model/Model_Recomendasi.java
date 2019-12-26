package com.prio.pariwisataserang.Model;

public class Model_Recomendasi {
    private String nama;
    private String photo;
    private String desc;
    private String alamat;
    private String rute;
    private String tiket;


    public Model_Recomendasi() {
    }

    public Model_Recomendasi(String nama, String photo, String desc, String alamat, String rute, String tiket) {
        this.nama = nama;
        this.photo = photo;
        this.desc = desc;
        this.alamat = alamat;
        this.rute = rute;
        this.tiket = tiket;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }
}
