package com.example.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.HomeFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.mainAD;
import com.example.myapplication.user.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private EditText edtEmail, edtPass;
    private RadioGroup radioGroup;
    private Button btnSignin;
    private RelativeLayout relativeLayout;
    private TextView tvLayoutForgetpass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtEmail = findViewById(R.id.edt_signin_email);
        edtPass = findViewById(R.id.edt_signin_pass);
        btnSignin = findViewById(R.id.btn_signin);
        //radioGroup = findViewById(R.id.radioGroup_signin);
        relativeLayout = findViewById(R.id.layout_signin);
        tvLayoutForgetpass = findViewById(R.id.layout_forgetpass);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this , SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        tvLayoutForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,ForgetPassActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Tài khoản không hợp lệ vui lòng thử lại", Toast.LENGTH_SHORT).show();

                }else {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(SignInActivity.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user!= null){
                                            //tài khoản tồn tại và kiểm tra quyền của tài khoản
                                            //lây ra id của user
                                            checkUserRole(user.getUid());
                                        }
                                    }else{
                                        Toast.makeText(SignInActivity.this, "Tài khoản không hợp lệ vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void checkUserRole(String uid) {
        mDatabase.child("users").child(uid).child("role")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String role = snapshot.getValue(String.class);
                            if (role != null) {
                                if (role.equals("user")) {
                                    // Xử lý khi vai trò là user
                                    Toast.makeText(SignInActivity.this, "Lấy dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                } else if (role.equals("admin")) {
                                    // Xử lý khi vai trò là admin
                                    Toast.makeText(SignInActivity.this, "Lấy dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(SignInActivity.this, mainAD.class);
                                    startActivity(intent1);
                                } else {
                                    // Xử lý khi vai trò không xác định
                                    Toast.makeText(SignInActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}