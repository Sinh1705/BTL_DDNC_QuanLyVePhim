package com.example.myapplication.user;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private Category_Adapter category_adapter;
    private MainActivity mainActivity ; //nhớ tạo biến môi trường
    private SearchView searchView;
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator3;
    private PhotoSligeAdapter photoSligeAdapter;
    private List<Photo> mphoto;

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.anh_1));
        return list;
    }
    @Nullable
            //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home, container, false);


        //viewPager2
        /*viewPager2 = view.findViewById(R.id.viewPager);
        indicator3 = view.findViewById(R.id.indicator);

        mphoto = getListPhoto();
        PhotoSligeAdapter adapter = new PhotoSligeAdapter();
        viewPager2.setAdapter(adapter);
        indicator3.setViewPager(viewPager2);*/


        //theloai
        recyclerView = view.findViewById(R.id.recyclerview_theloai);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        FirebaseRecyclerOptions<TheLoai> options = new
                FirebaseRecyclerOptions.Builder<TheLoai>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("category"), TheLoai.class)
                .build();
        category_adapter = new Category_Adapter(options);
        recyclerView.setAdapter(category_adapter);


       /* //phim
        recyclerView1 = view.findViewById(R.id.recycleview_phim);
        recyclerView1.setLayoutManager((new LinearLayoutManager(mainActivity)));
        FirebaseRecyclerOptions<PhimUser> options1 = new FirebaseRecyclerOptions.Builder<PhimUser>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"), PhimUser.class).build();
        phimUserAdapter = new PhimUserAdapter(options1);
        recyclerView1.setAdapter(phimUserAdapter);*/

        //tim kiem
        searchView = view.findViewById(R.id.search);
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

    public void textSearch(String str){
        FirebaseRecyclerOptions<TheLoai> options =
                new FirebaseRecyclerOptions.Builder<TheLoai>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category").orderByChild("theloai").startAt(str).endAt(str+"~"), TheLoai.class)
                        .build();

        Category_Adapter categoryAdapter = new Category_Adapter(options);
        categoryAdapter.startListening();
        recyclerView.setAdapter(categoryAdapter);
    }

    /*public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Category_Activity", "onAttach called");
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }*/
}
