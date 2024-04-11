package com.example.myapplication.user;

public class SinhVien {
    private String diachi, email, gioitinh, hoten, masinhvien, ngaysinh;
    private float diem;

    public SinhVien(String diachi, String email, String gioitinh, String hoten, String masinhvien, String ngaysinh, float diem) {
        this.diachi = diachi;
        this.email = email;
        this.gioitinh = gioitinh;
        this.hoten = hoten;
        this.masinhvien = masinhvien;
        this.ngaysinh = ngaysinh;
        this.diem = diem;
    }
    private SinhVien(){

    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
