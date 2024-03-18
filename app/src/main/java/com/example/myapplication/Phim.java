package com.example.myapplication;

public class Phim {
    private String ten;
    private String theloai;
    private String mota;
    private int gia;
    private String giokhoichieu;

    private String anhphim ;
    private String linkvideo;


    public Phim(String ten, String theloai, String mota, int gia, String giokhoichieu, String anhphim) {
        this.ten = ten;
        this.theloai = theloai;
        this.mota = mota;
        this.gia = gia;
        this.giokhoichieu = giokhoichieu;
        this.anhphim = anhphim;
    }
    public Phim(){}

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

    public int getGia() {
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

    public String getAnhphim() {
        return anhphim;
    }

    public void setAnhphim(String anhphim) {
        this.anhphim = anhphim;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }
}

