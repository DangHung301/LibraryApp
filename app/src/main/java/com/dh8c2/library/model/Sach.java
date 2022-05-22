package com.dh8c2.library.model;

public class Sach {
    private int idSach;
    private String tenSach;
    private int idLoaiSach;
    private String loaiSach;
    private String tacGia;
    private String namXuatBan;
    private String nhaXuatBan;
    private String moTa;
    private String imgSach;

    public String getImgSach() {
        return imgSach;
    }

    public void setImgSach(String imgSach) {
        this.imgSach = imgSach;
    }

    public Sach() {
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    public void setIdLoaiSach(int idLoaiSach) {
        this.idLoaiSach = idLoaiSach;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Sach(String tenSach, int idLoaiSach, String tacGia, String namXuatBan, String nhaXuatBan, String moTa, String img) {
        this.tenSach = tenSach;
        this.idLoaiSach = idLoaiSach;
        this.moTa = moTa;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.imgSach = img;
    }
}
