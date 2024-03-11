package com.example.myapplication.user;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.example.myapplication.TheLoaiAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Category_Activity extends Fragment {
    private RecyclerView recyclerView;
    private Category_Adapter category_adapter;

    private MainActivity mainActivity ; //nhớ tạo biến môi trường
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_theloai);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        FirebaseRecyclerOptions<TheLoai> options = new
                FirebaseRecyclerOptions.Builder<TheLoai>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("category"), TheLoai.class)
                .build();
        category_adapter = new Category_Adapter(options);
        recyclerView.setAdapter(category_adapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        category_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        category_adapter.stopListening();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("CategoryFragment", "onAttach called");
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }
}
