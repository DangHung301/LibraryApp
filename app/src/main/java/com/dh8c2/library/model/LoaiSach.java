package com.dh8c2.library.model;

public class LoaiSach {
    private int idLoaiSach;
    private String loaiSach;

    public LoaiSach() {
    }

    public LoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
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
}
