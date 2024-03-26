package com.example.myapplication.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    private  SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_phim);


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchPhim(s);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchPhim(s);
                return false;
            }
        });

    }

    private void searchPhim(String s) {
        FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").orderByChild("ten").startAt(s).endAt(s+"~"),Phim.class)
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
