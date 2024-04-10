package com.example.myapplication.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeachActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private RecyclerView mRecyclerView;
    private ImageView img_quaylai;
    private  SearchView searchView;
    private PhimUserAdapter phimUserAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_phim);

        img_quaylai = findViewById(R.id.img_quaylai);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();

        img_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeachActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                /*FirebaseRecyclerOptions<Phim> options1 = new FirebaseRecyclerOptions.Builder<Phim>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"), Phim.class).build();
                searchview_adapter adapter1 = new searchview_adapter(options1);
                adapter1.startListening();
                mRecyclerView.setAdapter(adapter1);*/
                searchPhim(s);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                /*FirebaseRecyclerOptions<Phim> options1 = new FirebaseRecyclerOptions.Builder<Phim>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"), Phim.class).build();
                searchview_adapter adapter1 = new searchview_adapter(options1);
                adapter1.startListening();
                mRecyclerView.setAdapter(adapter1);*/
                searchPhim(s);
                return false;
            }
        });

    }

    private void searchPhim(String s) {
        String searchKeyword = s.toLowerCase(); // Chuyển đổi từ khóa tìm kiếm về chữ thường để tìm kiếm không phân biệt hoa thường

        FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").orderByChild("ten").startAt(searchKeyword).endAt(searchKeyword+"~"),Phim.class)
                .build();
        searchview_adapter adapter = new searchview_adapter(options);
        adapter.setOnItemClickListener(new searchview_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Phim phim, int position) {
                Intent intent = new Intent(SeachActivity.this, Details_Film.class);
                intent.putExtra("phim_id", adapter.getRef(position).getKey());
                startActivity(intent);
            }
        });
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }

}
