package com.example.myapplication.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details_Category extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhimUserAdapter phimUserAdapter;
    String tentheloai = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_category);
        recyclerView = findViewById(R.id.recycleview_details_category);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        String categoryId = getIntent().getStringExtra("category_id");

        FirebaseDatabase.getInstance().getReference().child("category").child(categoryId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            TheLoai theLoai = snapshot.getValue(TheLoai.class);
                            if(theLoai!=null){
                                tentheloai = theLoai.getTheloai();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý khi có lỗi
                    }
                });

        //truy van ds phim thuoc the loai va hien thi
        FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").child(tentheloai), Phim.class)
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
