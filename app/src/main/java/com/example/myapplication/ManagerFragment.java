package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Account.ChangePassActivity;
import com.example.myapplication.Account.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerFragment extends Fragment {
    private ImageView imgDoanhthu , imgMatKhau , imgLogout;
    private TextView tvEmail;

    private FirebaseUser mAuth;
    private mainAD mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_accounts,container,false);
        imgDoanhthu = view.findViewById(R.id.img_doanhthu);
        imgMatKhau = view.findViewById(R.id.img_doimatkhau);
        imgLogout = view.findViewById(R.id.img_logout);
        tvEmail = view.findViewById(R.id.tv_email);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if(mAuth !=null){
            String email = mAuth.getEmail();
            tvEmail.setText(email);
        }

        imgMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, ChangePassActivity.class);
                startActivity(intent);
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(mainActivity, SignInActivity.class);
                //quay lại màn hình và không lưu trạng thái của trang
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    //nhớ tạo onAttach
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof mainAD) {
            mainActivity = (mainAD) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }
}
