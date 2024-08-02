package com.example.bookstore;

import java.time.LocalDateTime;

public class BillData {
    private int MaHd;
    private LocalDateTime Ngayin;
    private int TongTien;

    public int getMaHd() {
        return MaHd;
    }

    public void setMaHd(int maHd) {
        MaHd = maHd;
    }

    public LocalDateTime getNgayin() {
        return Ngayin;
    }

    public void setNgayin(LocalDateTime ngayin) {
        Ngayin = ngayin;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public BillData(int maHd, LocalDateTime ngayin, int tongTien) {
        MaHd = maHd;
        Ngayin = ngayin;
        TongTien = tongTien;
    }
}
