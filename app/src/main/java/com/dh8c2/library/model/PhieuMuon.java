package com.dh8c2.library.model;

public class PhieuMuon {
    private int idPhieuMuon;
    private String tenSv;
    private String maSv;
    private int idSach;
    private String lop;
    private String ngayMuon;
    private String ngayTra;
    private int daTra;
    private String tenSach;

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getDaTra() {
        return daTra;
    }

    public void setDaTra(int daTra) {
        this.daTra = daTra;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(String tenSv, String maSv, int idSach, String lop, String ngayMuon, String ngayTra, int daTra, String tenSach) {
        this.tenSv = tenSv;
        this.maSv = maSv;
        this.idSach = idSach;
        this.lop = lop;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.daTra = daTra;
        this.tenSach = tenSach;
    }

    public int getIdPhieuMuon() {
        return idPhieuMuon;
    }

    public void setIdPhieuMuon(int idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
