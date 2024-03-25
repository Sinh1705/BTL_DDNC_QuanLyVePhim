package com.example.myapplication.user;

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
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerActivity extends Fragment {
    private ImageView imgChangePass, imgLogout;
    private TextView txtEmail;
    private FirebaseUser mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_manager, container, false);
        imgChangePass = view.findViewById(R.id.img_change_password);
        imgLogout = view.findViewById(R.id.img_logout);
        txtEmail = view.findViewById(R.id.txt_email);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth != null) {
            String email = mAuth.getEmail();
            txtEmail.setText(email);
        }

        imgChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), ChangePassActivity.class));
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(requireContext(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}
