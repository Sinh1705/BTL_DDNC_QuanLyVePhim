package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddPhim extends AppCompatActivity {
    private Spinner spinner;
    private EditText ten, mota, gia, khoichieu, linkanh, linkbanner, linkvideo;
    private Button btnThem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phim);

        ten = findViewById(R.id.edt_add_tenphim);
        mota = findViewById(R.id.edt_mota);
        gia = findViewById(R.id.edt_gia);
        khoichieu = findViewById(R.id.edt_khoichieu);
        linkanh = findViewById(R.id.edt_linkanh);
        linkbanner = findViewById(R.id.edt_linkanh_banner);
        linkvideo = findViewById(R.id.edt_add_linkvideo);
        spinner = findViewById(R.id.sp_category);
        btnThem = findViewById(R.id.btn_add_phim);

        FirebaseDatabase.getInstance().getReference().child("category")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //tạo mảng để chứa dữ liệu firebase
                        ArrayList<String> arrayList = new ArrayList<>();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            //lấy ra thuoc tính cụ thể
                            String spinner_name = dataSnapshot.child("theloai").getValue(String.class);
                            arrayList.add(spinner_name);
                        }

                        //tạo arrayadapter để hiện thị dữ liệu lên spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddPhim.this, android.R.layout.simple_spinner_item,arrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearData();
            }
        });
    }

    private void clearData() {
        ten.setText("");
        mota.setText("");
        linkvideo.setText("");
        khoichieu.setText("");
        gia.setText("");
        linkanh.setText("");
        linkbanner.setText("");
    }

    private void insertData() {
        //lấy các dữ liệu từ edittext đẩy vào map . map chứa object
        //map đẩy lên firebase
        Map<String, Object> map = new HashMap<>();
        //lấy vị trí được chọn spinner
        int position = spinner.getSelectedItemPosition();
        String value = gia.getText().toString();
        int gia = Integer.parseInt(value.toString());
        map.put("theloai",spinner.getItemAtPosition(position).toString());
        map.put("ten",ten.getText().toString());
        map.put("mota",mota.getText().toString());
        map.put("linkvideo",linkvideo.getText().toString());
        map.put("giokhoichieu",khoichieu.getText().toString());
        map.put("gia",gia);
        map.put("anhphim",linkanh.getText().toString());
        map.put("anhbannner",linkbanner.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("phim").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddPhim.this,"Data insert successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddPhim.this,"Data not insert successfully",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
