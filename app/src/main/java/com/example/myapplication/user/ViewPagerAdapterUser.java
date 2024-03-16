package com.example.myapplication.user;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterUser extends FragmentStateAdapter {
    public ViewPagerAdapterUser(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }


    @NonNull
   // @Override
public Fragment createFragment(int position) {
        switch (position){

case 0: return new HomeActivity();
            case 1: return new HistoryActivity();
            case 2: return new ManangerActivity();
            default: return new HomeActivity();
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
