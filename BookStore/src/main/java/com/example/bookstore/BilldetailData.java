package com.example.bookstore;

public class BilldetailData {
    private int Id;
    private int MaHd;
    private String TenSp; // string -> int
    private int SoLuong;
    private int Gia;
    private int TongTien;

    public BilldetailData(int id, int maHd, String tenSp, int soLuong, int gia, int tongTien) {
        Id = id;
        MaHd = maHd;
        TenSp = tenSp;
        SoLuong = soLuong;
        Gia = gia;
        TongTien = tongTien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMaHd() {
        return MaHd;
    }

    public void setMaHd(int maHd) {
        MaHd = maHd;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }
}
