package com.example.myapplication.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details_Film extends AppCompatActivity {
    private TextView tvTen,tvTheLoai, tvKhoiChieu, tvGia, tvMota;
    private Button btnTrailer, btnDatve;
    private VideoView videoView;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_film_xml);
        tvTen = findViewById(R.id.detail_ten);
        tvTheLoai = findViewById(R.id.detail_theloai);
        tvKhoiChieu = findViewById(R.id.detail_khoichieu);
        tvGia = findViewById(R.id.detail_gia);
        tvMota = findViewById(R.id.detail_mota);
        btnTrailer = findViewById(R.id.btn_trailer);
        btnDatve = findViewById(R.id.btn_datve);
        videoView = findViewById(R.id.video_trailer);
        //lấy id phim từ intent phimUserAdapter
        String phimID = getIntent().getStringExtra("phim_id");
        //truy vấn dữ liệu từ firebase
        FirebaseDatabase.getInstance().getReference().child("phim").child(phimID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            Phim phim = snapshot.getValue(Phim.class);
                            if(phim != null){
                                tvTen.setText(phim.getTen());
                                tvTheLoai.setText(phim.getTheloai());
                                tvKhoiChieu.setText(phim.getGiokhoichieu());
                                tvGia.setText(String.valueOf(phim.getGia()));
                                tvMota.setText(phim.getMota());

                                Uri uri = Uri.parse(phim.getLinkvideo());
                                videoView.setVideoURI(uri);

                                MediaController mediaController = new MediaController(Details_Film.this);
                                videoView.setMediaController(mediaController);
                                mediaController.setAnchorView(videoView);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin phim", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        //bấm nút xem trailer
        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });

        btnDatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Details_Film.this,Buy_StickActivity.class);
                startActivity(intent);
            }
        });

    }
}
