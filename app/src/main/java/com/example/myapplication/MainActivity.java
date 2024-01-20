package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //String test="day la du lieu moi";
    RecyclerView recyclerView;
    PhimAdapter phimAdapter;

    List<Phim> mListPhim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_buy_ticket);

        /*recyclerView = findViewById(R.id.recyclerview);
        mListPhim = new ArrayList<>();
        Phim phim1 = new Phim(R.drawable.anh_1,"1","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim2 = new Phim(R.drawable.anh_1,"2","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim3 = new Phim(R.drawable.anh_1,"3","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim4 = new Phim(R.drawable.anh_1,"4","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim5 = new Phim(R.drawable.anh_1,"5","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim6 = new Phim(R.drawable.anh_1,"6","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
        Phim phim7 = new Phim(R.drawable.anh_1,"7","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
        mListPhim.add(phim1);
        mListPhim.add(phim2);
        mListPhim.add(phim3);
        mListPhim.add(phim4);
        mListPhim.add(phim5);
        mListPhim.add(phim6);
        mListPhim.add(phim7);

        phimAdapter = new PhimAdapter(mListPhim);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phimAdapter);*/
    }

}