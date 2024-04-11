package com.example.myapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.example.myapplication.TheLoaiAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class danhsachSV extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SinhVienAdapter sinhVienAdapter;
    private MainActivity mainActivity;
    private Button btnThem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachsv);

        recyclerView = findViewById(R.id.recycal_sinhvien);
        btnThem = findViewById(R.id.btn_themsinhvien);

        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        FirebaseRecyclerOptions<SinhVien> options = new
                FirebaseRecyclerOptions.Builder<SinhVien>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("sinhvien"),SinhVien.class)
                .build();
        sinhVienAdapter = new SinhVienAdapter(options);
        recyclerView.setAdapter(sinhVienAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(danhsachSV.this, ThemSinhVien.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        sinhVienAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        sinhVienAdapter.stopListening();
    }
}
