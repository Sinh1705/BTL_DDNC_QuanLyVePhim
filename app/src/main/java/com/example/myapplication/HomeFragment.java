package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
      private RecyclerView recyclerView;
      private PhimAdapter phimAdapter;

      private MainActivity mainActivity; //nhớ tạo biến môi trường
      List<Phim> mListPhim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_home);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phimAdapter);
        return view;
    }


}
