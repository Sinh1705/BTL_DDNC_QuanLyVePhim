package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
      private RecyclerView recyclerView;
      private PhimAdapter phimAdapter;

      private mainAD mainActivity;//nhớ tạo biến môi trường
      private SearchView searchView;
      private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        FirebaseRecyclerOptions<Phim> options = new FirebaseRecyclerOptions.Builder<Phim>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"), snapshot -> {
                    String ten = String.valueOf(snapshot.child("ten").getValue());
                    String theloai = String.valueOf(snapshot.child("theloai").getValue());
                    String mota = String.valueOf(snapshot.child("mota").getValue());
                    int gia = snapshot.child("gia").getValue(Integer.class);
                    String giokhoichieu = String.valueOf(snapshot.child("giokhoichieu").getValue());
                    String anhphim = String.valueOf(snapshot.child("anhphim").getValue());
                    return new Phim(ten, theloai, mota, gia, giokhoichieu, anhphim);
                })
                .build();
//        FirebaseRecyclerOptions<Phim> options = new
//                FirebaseRecyclerOptions.Builder<Phim>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"),Phim.class)
//                .build();
        phimAdapter = new PhimAdapter(options);
        recyclerView.setAdapter(phimAdapter);


        //hiện thị thể loại lên flowlayout
        FlowLayout flowLayout = view.findViewById(R.id.flow_layout);

        FirebaseDatabase.getInstance().getReference().child("category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        flowLayout.removeAllViews();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            String name = dataSnapshot.child("theloai").getValue(String.class);

                            TextView textView = new TextView(view.getContext());
                            //trong 1 lớp không hoạt đông như activity
                            textView.setText(name);

                            //thiết lập margin , padding cho textview
                            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(5,5,5,5);
                            textView.setLayoutParams(layoutParams);
                            textView.setPadding(10,10,10,10);

                            //thiết lập background cho textview
                            textView.setBackgroundResource(R.drawable.custom_flowlayout);
                            flowLayout.addView(textView);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        //khi người dùng click vào floatingButton
        floatingActionButton = view.findViewById(R.id.fabutton_phim);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, AddPhim.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        phimAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        phimAdapter.stopListening();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof mainAD) {
            mainActivity = (mainAD) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }
}
