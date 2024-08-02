package com.example.bookstore;

public class ProductData {
    String mota;
    String masp;
    String tensp;

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public String getTheloai() {
        return Theloai;
    }

    public void setTheloai(String theloai) {
        Theloai = theloai;
    }

    String Tacgia;
    String Theloai;
    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public ProductData(String masp, String mota,String tensp, int maloai,int gia , String Tacgia , String Theloai) {
        this.mota = mota;
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.gia = gia;
        this.Tacgia = Tacgia;
        this.Theloai = Theloai;
    }

    int maloai;
    int gia;
}
