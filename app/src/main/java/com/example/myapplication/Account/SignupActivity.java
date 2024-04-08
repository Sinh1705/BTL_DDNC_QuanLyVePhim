package com.example.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    private EditText edtEmail , edtPass;
    private RadioGroup radioGroup;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private RelativeLayout relativeLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtEmail = findViewById(R.id.edt_signup_email);
        edtPass = findViewById(R.id.edt_signup_pass);
        //radioGroup = findViewById(R.id.radioGroup_signup);
        btnSignup = findViewById(R.id.btn_signup);
        relativeLayout = findViewById(R.id.layout_signup);

        //khi người dùng bấm đã có tài khoản đăng nhập
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Khởi tạo DatabaseReference

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = edtPass.getText().toString();

                if (password.length() < 6) {
                    edtPass.setError("Mật khẩu phải có ít nhất 6 ký tự");
                }

                if (!password.matches(".*[A-Z].*")) {
                    edtPass.setError("Mật khẩu phải có ít nhất một chữ in hoa");
                    return;
                }

                if (!password.matches(".*[a-z].*")) {
                    edtPass.setError("Mật khẩu phải có ít nhất một chữ thường");
                    return;
                }

                if (!password.matches(".*[@#$%^&+=].*")) {
                    edtPass.setError("Mật khẩu phải có ít nhất một kí tự đặc biệt");
                    return;
                }

                if (!password.matches(".*\\d.*")) {
                    edtPass.setError("Mật khẩu phải có ít nhất một số");
                    return;
                }

            }
        });


        //đăng ký và check tài khoản
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                String role = "user";

                //kiểm tra email đã tồn tại trong realtimedatabase chưa
                //String finalRole = role;
                databaseReference.child("users").orderByChild("email").equalTo(email)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    //email đã tồn tại cập nhât quyền cho tài khoản hiện tại
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        //lấy ra ID người dùng
                                        //String userID = dataSnapshot.getKey();

                                        //cập nhật quyền
                                        //dataSnapshot.getRef().child("role").child(finalRole).setValue(true);
                                        Toast.makeText(SignupActivity.this, "Email đăng kí đã tồn tại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    registeraccount(email,pass,role);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

            }
        });

    }
    private void registeraccount(String email, String password , String role){
        // Đăng ký tài khoản mới
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng ký thành công, lưu thông tin vào Realtime Database
                            String userId = mAuth.getCurrentUser().getUid();

                            // Tạo một nút mới trong Realtime Database dưới đường dẫn "users"
                            databaseReference.child("users").child(userId).child("email").setValue(email);
                            databaseReference.child("users").child(userId).child("password").setValue(password);
                            databaseReference.child("users").child(userId).child("role").setValue(role);

                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // Đăng ký thất bại
                            Toast.makeText(SignupActivity.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}