package com.example.myapplication.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Details_Category extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhimUserAdapter phimUserAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_category);
        recyclerView = findViewById(R.id.recycleview_details_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String categoryId = getIntent().getStringExtra("category_id");

        //truy van ds phim thuoc the loai va hien thi
        FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").child(categoryId), Phim.class)
                .build();
        phimUserAdapter = new PhimUserAdapter(options, this);
        recyclerView.setAdapter(phimUserAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        phimUserAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        phimUserAdapter.stopListening();
    }
}
