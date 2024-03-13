package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //String test="day la du lieu moi";
//    RecyclerView recyclerView;
//    PhimAdapter phimAdapter;
//
//    List<Phim> mListPhim;
    List<TheLoai> theloai = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;

    private ViewPagerAdapter viewPagerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);

//        recyclerView = findViewById(R.id.recyclerview_home);
//        mListPhim = new ArrayList<>();
//        Phim phim1 = new Phim(R.drawable.anh_1,"1","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim2 = new Phim(R.drawable.anh_1,"2","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim3 = new Phim(R.drawable.anh_1,"3","Hoạt hình","Kể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim4 = new Phim(R.drawable.anh_1,"4","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim5 = new Phim(R.drawable.anh_1,"5","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim6 = new Phim(R.drawable.anh_1,"6","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
//        Phim phim7 = new Phim(R.drawable.anh_1,"7","Hoạt hình","K+ể về cuộc phưu lưu ",12,"12/5/22024");
//        mListPhim.add(phim1);
//        mListPhim.add(phim2);
//        mListPhim.add(phim3);
//        mListPhim.add(phim4);
//        mListPhim.add(phim5);
//        mListPhim.add(phim6);
//        mListPhim.add(phim7);
//
//        phimAdapter = new PhimAdapter(mListPhim);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(phimAdapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
        setUpViewPager();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.action_category) {
                    // Handle Category selection
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.action_home) {
                    // Handle Home selection
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.action_history) {
                    // Handle History selection
                    viewPager.setCurrentItem(2);
                } else if (itemId == R.id.action_manager) {
                    // Handle Manager selection
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }


    private void setUpViewPager() {

        //khi người dùng lướt chuyển trang
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0 :
                        bottomNavigationView.getMenu().findItem(R.id.action_category).setChecked(true);
                        break;
                    case 1 :
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 2 :
                        bottomNavigationView.getMenu().findItem(R.id.action_history).setChecked(true);
                        break;
                    case 3 :
                        bottomNavigationView.getMenu().findItem(R.id.action_manager).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}