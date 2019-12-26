package com.prio.pariwisataserang.Model;

public class Model_Pariwisata {

    private String nama;
    private String photo;
    private String desc;
    private String alamat;
    private String fasilitas;
    private String tiket;
    private String id;

    public Model_Pariwisata() {
    }

    public Model_Pariwisata(String nama, String photo, String desc, String alamat,
                            String fasilitas, String tiket, String id) {
        this.nama = nama;
        this.photo = photo;
        this.desc = desc;
        this.alamat = alamat;
        this.fasilitas = fasilitas;
        this.tiket = tiket;
        this.id = id;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }
}
