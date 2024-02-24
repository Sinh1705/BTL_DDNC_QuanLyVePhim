package com.example.myapplication;

public class TheLoai {
    private String linkanh;
    private String theloai;

    public TheLoai(String linkanh, String theloai) {
        this.linkanh = linkanh;
        this.theloai = theloai;
    }
    public TheLoai(){

    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
