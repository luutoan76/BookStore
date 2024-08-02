package com.example.bookstore;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CategoryData {
    private Connection connection;
    int Maloai;
    String TenLoai;


    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public CategoryData(int maloai, String tenLoai, String moTa) {
        Maloai = maloai;
        TenLoai = tenLoai;
        MoTa = moTa;
    }

    String MoTa;
}
