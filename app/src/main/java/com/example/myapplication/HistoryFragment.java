package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.user.History_Adapter;
import com.example.myapplication.user.Ve;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private mainAD mainAD;
    private HistoryAdmin_Adapter historyAdmin_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainAD,LinearLayoutManager.VERTICAL, false));


        FirebaseRecyclerOptions<Ve> options = new FirebaseRecyclerOptions.Builder<Ve>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ticket"), Ve.class).build();
        historyAdmin_adapter = new HistoryAdmin_Adapter(options,getContext());
        recyclerView.setAdapter(historyAdmin_adapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        historyAdmin_adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        historyAdmin_adapter.stopListening();

    }

}
