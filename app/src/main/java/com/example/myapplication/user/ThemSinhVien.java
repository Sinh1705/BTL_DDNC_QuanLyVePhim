package com.example.myapplication.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.AddPhim;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ThemSinhVien extends AppCompatActivity {
    EditText masv , hoten , diachi, ngaysinh, gioitinh, email, diemhotap;
    Button btnThem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsinhvien);
        btnThem = findViewById(R.id.btn_themsv);
        masv = findViewById(R.id.edt_masinhvien);
        hoten = findViewById(R.id.edt_hotensv);
        ngaysinh = findViewById(R.id.edt_ngaysinhsv);
        diachi = findViewById(R.id.edt_diachisv);
        gioitinh = findViewById(R.id.edt_gioitinh);
        email = findViewById(R.id.edt_emailsv);
        diemhotap = findViewById(R.id.edt_diemsv);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {
        //lấy các dữ liệu từ edittext đẩy vào map . map chứa object
        //map đẩy lên firebase
        Map<String, Object> map = new HashMap<>();
        //lấy vị trí được chọn spinner
        String value = diemhotap.getText().toString();
        int diem = Integer.parseInt(value.toString());
        map.put("diachi",diachi.getText().toString());
        map.put("diem",diem);
        map.put("email",email.getText().toString());
        map.put("gioitinh",gioitinh.getText().toString());
        map.put("hoten",hoten.getText().toString());
        map.put("masinhvien",masv.getText().toString());
        map.put("ngaysinh",ngaysinh.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("sinhvien").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ThemSinhVien.this,"Data insert successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(ThemSinhVien.this,"Data not insert successfully",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }

