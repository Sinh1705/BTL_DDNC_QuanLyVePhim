package com.example.myapplication.user;

import java.util.ArrayList;

public class Ve {
    private int maphim, soluongve;
    private String gio, khoichieu, ngaydat, phong, taikhoandat, tenphim;
    private double tongtien;
    private ArrayList<String> ghe;

    public Ve(int maphim, int soluongve, String gio, String khoichieu, String ngaydat, String phong, String taikhoandat, String tenphim, double tongtien, ArrayList<String> ghe) {
        this.maphim = maphim;
        this.soluongve = soluongve;
        this.gio = gio;
        this.khoichieu = khoichieu;
        this.ngaydat = ngaydat;
        this.phong = phong;
        this.taikhoandat = taikhoandat;
        this.tenphim = tenphim;
        this.tongtien = tongtien;
        this.ghe = ghe;
    }

    public Ve(){

    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }

    public int getSoluongve() {
        return soluongve;
    }

    public void setSoluongve(int soluongve) {
        this.soluongve = soluongve;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getKhoichieu() {
        return khoichieu;
    }

    public void setKhoichieu(String khoichieu) {
        this.khoichieu = khoichieu;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getTaikhoandat() {
        return taikhoandat;
    }

    public void setTaikhoandat(String taikhoandat) {
        this.taikhoandat = taikhoandat;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public ArrayList<String> getGhe() {
        return ghe;
    }

    public void setGhe(ArrayList<String> ghe) {
        this.ghe = ghe;
    }
}
