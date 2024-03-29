package com.example.myapplication.user;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Phim;
import com.example.myapplication.PhimAdapter;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.example.myapplication.TheLoaiAdapter;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String categoryId = getIntent().getStringExtra("category_id");

        // Truy vấn Firebase để lấy tên của thể loại từ category_id
        FirebaseDatabase.getInstance().getReference().child("category").child(categoryId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            String tenTheLoai = snapshot.child("theloai").getValue(String.class);
                                // Truy vấn danh sách phim từ cơ sở dữ liệu Firebase bằng tên thể loại
                            FirebaseRecyclerOptions<Phim> options =
                                    new FirebaseRecyclerOptions.Builder<Phim>()
                                            .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").orderByChild("theloai").equalTo(tenTheLoai), Phim.class)
                                            .build();

                            phimUserAdapter = new PhimUserAdapter(options,Details_Category.this);
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
