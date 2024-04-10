package com.example.myapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.example.myapplication.user.PhimUserAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details_Category extends AppCompatActivity {
    private ImageView quaylai;
    private TextView txt_theloai;
    private RecyclerView recyclerView;
    private PhimUserAdapter phimUserAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_category);
        txt_theloai = findViewById(R.id.txt_category);
        quaylai = findViewById(R.id.img_quaylai);
        recyclerView = findViewById(R.id.recycleview_details_category);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details_Category.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        String categoryId = getIntent().getStringExtra("category_id");

        FirebaseDatabase.getInstance().getReference().child("category").child(categoryId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            TheLoai theLoai = snapshot.getValue(TheLoai.class);
                            txt_theloai.setText(theLoai.getTheloai());

                            //truy van ds phim thuoc the loai va hien thi
                            FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").orderByChild(categoryId), Phim.class)
                                    .build();
                            phimUserAdapter = new PhimUserAdapter(options, Details_Category.this);
                            phimUserAdapter.startListening();
                            recyclerView.setAdapter(phimUserAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý khi có lỗi
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (phimUserAdapter != null) {
            phimUserAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (phimUserAdapter != null) {
            phimUserAdapter.stopListening();
        }
    }
}