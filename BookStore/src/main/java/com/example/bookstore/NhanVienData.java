package com.example.bookstore;

public class NhanVienData {

    private int ID;
    private String Ten;
    private String Email;
    private String Sdt;
    private int Tuoi;

    public NhanVienData(int ID, String ten, String email, String sdt, int tuoi, String role, String pass) {
        this.ID = ID;
        Ten = ten;
        Email = email;
        Sdt = sdt;
        Tuoi = tuoi;
        Role = role;
        Pass = pass;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    private String Role;
    private String Pass;


}
