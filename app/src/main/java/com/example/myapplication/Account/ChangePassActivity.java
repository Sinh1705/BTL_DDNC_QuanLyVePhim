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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassActivity extends AppCompatActivity {
    private EditText edtpass , edtnewpass , edtcheckpass;
    private Button btnCheckpass;
    private FirebaseUser user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        edtpass = findViewById(R.id.edt_password_change);
        edtnewpass = findViewById(R.id.edt_password_new);
        edtcheckpass = findViewById(R.id.edt_password_check);
        btnCheckpass = findViewById(R.id.btn_pass);
        user = FirebaseAuth.getInstance().getCurrentUser();

        btnCheckpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edtpass.getText().toString();
                String newpass = edtnewpass.getText().toString();
                String checkpass = edtcheckpass.getText().toString();

                if(!newpass.equals(checkpass)){
                    Toast.makeText(ChangePassActivity.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user !=null){
                    //AuthCredential là 1 lớp trong firebaseAuthentication xác thực thông tin người dùng
                    AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(),pass);
                    //reauthenticate() trong Firebase Authentication được sử dụng để xác thực lại người dùng trước khi thực hiện các thao tác như đổi mật khẩu
                    user.reauthenticate(authCredential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        //nếu xác thực mật khẩu cũ thành công tiến hành đổi mật khẩu
                                        user.updatePassword(newpass)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(ChangePassActivity.this, "Đổi mật khâu thành công", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        }else {
                                                            Toast.makeText(ChangePassActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }else {
                                        Toast.makeText(ChangePassActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
