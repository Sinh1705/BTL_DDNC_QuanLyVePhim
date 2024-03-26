package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.user.ViewPagerAdapterUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
    private ViewPagerAdapterUser viewPagerAdapterUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        //user
        bottomNavigationView = findViewById((R.id.bottom_navigations));
        viewPager = findViewById(R.id.view_pagers);
        viewPagerAdapterUser = new ViewPagerAdapterUser(getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter((viewPagerAdapterUser));
        setUpViewPager();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Category selection
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.action_history) {
                    // Handle Home selection
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.action_manager) {
                    // Handle Manager selection
                    viewPager.setCurrentItem(2);
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
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1 :
                        bottomNavigationView.getMenu().findItem(R.id.action_history).setChecked(true);
                        break;
                    case 2 :
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