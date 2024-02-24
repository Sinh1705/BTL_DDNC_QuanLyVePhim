package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddTheLoai extends AppCompatActivity {
    EditText edt_name, edt_link;
    Button btnThem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_theloai);

        edt_name = findViewById(R.id.edt_add_ten);
        edt_link = findViewById(R.id.edt_add_linkanh);
        btnThem = findViewById(R.id.btn_add_theloai);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                claerAll();
            }
        });

    }

    private void claerAll() {
        edt_name.setText("");
        edt_link.setText("");
    }

    private void insertData() {
        //lấy các dữ liệu từ edittext đẩy vào map . map chứa object
        //map đẩy lên firebase
        Map<String, Object> map = new HashMap<>();
        map.put("theloai", edt_name.getText().toString());
        map.put("linkanh",edt_link.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("category").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddTheLoai.this,"Data insert successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddTheLoai.this,"Data not insert successfully",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
