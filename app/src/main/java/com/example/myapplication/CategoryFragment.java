package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private TheLoaiAdapter theLoaiAdapter;

    private mainAD mainAD ; //nhớ tạo biến môi trường

    private SearchView searchView;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_theloai);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainAD));
        FirebaseRecyclerOptions<TheLoai> options = new
                FirebaseRecyclerOptions.Builder<TheLoai>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("category"),TheLoai.class)
                .build();
        theLoaiAdapter = new TheLoaiAdapter(options);
        recyclerView.setAdapter(theLoaiAdapter);

        //tìm kiếm theo tên thể loại
        searchView = view.findViewById(R.id.searchView_category);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });


        //thêm 1 thể loại
        floatingActionButton = view.findViewById(R.id.fabutton_theloai);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CategoryFragment", "FloatingActionButton clicked");
                Intent intent = new Intent(mainAD, AddTheLoai.class);
                startActivity(intent);
            }
        });


        //xóa từng item của recycalview
        theLoaiAdapter.setOnDeleteItemClickListenner(new TheLoaiAdapter.OnDeleteItemClickListenner() {
            @Override
            public void OnDeleteItemClick(int position) {
                //xóa khỏi mục recycalview
                theLoaiAdapter.getSnapshots().getSnapshot(position).getRef().removeValue();
            }
        });


        return view;


    }

    //nhớ start , stop
    @Override
    public void onStart() {
        super.onStart();
        theLoaiAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        theLoaiAdapter.stopListening();
    }

    public void textSearch(String str){
        FirebaseRecyclerOptions<TheLoai> options =
                new FirebaseRecyclerOptions.Builder<TheLoai>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category").orderByChild("theloai").startAt(str).endAt(str+"~"), TheLoai.class)
                        .build();

        TheLoaiAdapter theLoaiAdapter1 = new TheLoaiAdapter(options);
        theLoaiAdapter1.startListening();
        recyclerView.setAdapter(theLoaiAdapter1);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("CategoryFragment", "onAttach called");
        if (context instanceof mainAD) {
            mainAD = (mainAD) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }



}
