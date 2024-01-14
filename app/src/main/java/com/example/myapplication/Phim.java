package com.example.myapplication;

public class Phim {
    private int hinh;
    private String ten;
    private String theloai;
    private String mota;
    private int gia;
    private String giokhoichieu;

    public Phim(int hinh, String ten, String theloai, String mota, int gia, String giokhoichieu) {
        this.hinh = hinh;
        this.ten = ten;
        this.theloai = theloai;
        this.mota = mota;
        this.gia = gia;
        this.giokhoichieu = giokhoichieu;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getGiokhoichieu() {
        return giokhoichieu;
    }

    public void setGiokhoichieu(String giokhoichieu) {
        this.giokhoichieu = giokhoichieu;
    }
}
