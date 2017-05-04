package com.rafilutfansyah.robogensiswa.model;

/**
 * Created by Rafi Lutfansyah on 29/04/2017.
 */

public class RaportModel {

    private String idRaport, username, materi, foto, grade, keterangan, hari, tanggal, jamMasuk;

    private int nilaiMerakit, nilaiMandiri, nilaiKreativitas;

    private Double nilaiTotal;

    public RaportModel() {
        this.idRaport = idRaport;
        this.username = username;
        this.materi = materi;
        this.foto = foto;
        this.grade = grade;
        this.keterangan = keterangan;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jamMasuk = jamMasuk;
        this.nilaiMerakit = nilaiMerakit;
        this.nilaiMandiri = nilaiMandiri;
        this.nilaiKreativitas = nilaiKreativitas;
        this.nilaiTotal = nilaiTotal;
    }

    public String getIdRaport() {
        return idRaport;
    }

    public void setIdRaport(String idRaport) {
        this.idRaport = idRaport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public int getNilaiMerakit() {
        return nilaiMerakit;
    }

    public void setNilaiMerakit(int nilaiMerakit) {
        this.nilaiMerakit = nilaiMerakit;
    }

    public int getNilaiMandiri() {
        return nilaiMandiri;
    }

    public void setNilaiMandiri(int nilaiMandiri) {
        this.nilaiMandiri = nilaiMandiri;
    }

    public int getNilaiKreativitas() {
        return nilaiKreativitas;
    }

    public void setNilaiKreativitas(int nilaiKreativitas) {
        this.nilaiKreativitas = nilaiKreativitas;
    }

    public Double getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(Double nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }
}
