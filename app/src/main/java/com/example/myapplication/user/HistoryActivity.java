package com.example.myapplication.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends Fragment {

    private RecyclerView recyclerView;
    private MainActivity mainActivity;
    private History_Adapter history_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_history_ticket, container, false);
        recyclerView = view.findViewById(R.id.user_history_recycalview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL, false));

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String tentaikhoan = "";
        if(firebaseUser !=null){
            tentaikhoan = firebaseUser.getEmail();
        }
        FirebaseRecyclerOptions<Ve> options = new FirebaseRecyclerOptions.Builder<Ve>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ticket").orderByChild("taikhoandat").equalTo(tentaikhoan), Ve.class).build();
        history_adapter = new History_Adapter(options,getContext());
        recyclerView.setAdapter(history_adapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        history_adapter.startListening();


    }

    @Override
    public void onStop() {
        super.onStop();
        history_adapter.stopListening();

    }

    }
