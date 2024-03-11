package com.example.myapplication.Account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPassActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnForgetPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        edtEmail = findViewById(R.id.edt_forget_pass);
        btnForgetPass = findViewById(R.id.btn_forget_pass);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString();
                //laays ra uers hien tai
                if(mAuth.getCurrentUser()!=null){
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgetPassActivity.this, "Yêu cầu đặt lại mật khẩu đã được gửi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(ForgetPassActivity.this, "Yêu cầu đặt lại mật khẩu không thành công", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}
