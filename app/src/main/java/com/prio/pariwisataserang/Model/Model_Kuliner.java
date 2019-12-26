package com.prio.pariwisataserang.Model;

public class Model_Kuliner {
    private String nama;
    private String time;
    private String tipe;
    private String alamat;
    private String photo;
    private  String rute;
    private String desc;

    public Model_Kuliner(String nama, String time, String tipe, String alamat, String photo, String rute, String desc) {
        this.nama = nama;
        this.time = time;
        this.tipe = tipe;
        this.alamat = alamat;
        this.photo = photo;
        this.rute = rute;
        this.desc = desc;
    }

    public Model_Kuliner() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String jenis) {
        this.time = jenis;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
