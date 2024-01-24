package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new CategoryFragment();
            case 1 :
                return new HomeFragment();
            case 2 :
                return new HistoryFragment();
            case 3:
                return new ManagerFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


//    public ViewPagerAdapter(@NonNull Fragment fragment) {
//        super(fragment);
//    }

//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position){
//            case 0 :
//                return new CategoryFragment();
//            case 1 :
//                return new HomeFragment();
//            case 2 :
//                return new HistoryFragment();
//            case 3:
//                return new ManagerFragment();
//            default:
//                return new HomeFragment();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 4;
//    }

}
