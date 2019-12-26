package com.prio.pariwisataserang.Model;

public class Model_Terbaik {
    private String nama,photo,alamat
            ,desc,type,harga;

    public Model_Terbaik() {
    }

    public Model_Terbaik(String nama, String photo, String alamat, String desc, String type, String harga) {
        this.nama = nama;
        this.photo = photo;
        this.alamat = alamat;
        this.desc = desc;
        this.type = type;
        this.harga = harga;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
