package com.example.myapplication.user;



public class PhimUser {
    private String Ten;
    private String AnhPhim;

    public PhimUser(String Ten, String AnhPhim){
        this.Ten = Ten;
        this.AnhPhim = AnhPhim;
    }

    public PhimUser(){}

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getAnhPhim() {
        return AnhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        AnhPhim = anhPhim;
    }
}
