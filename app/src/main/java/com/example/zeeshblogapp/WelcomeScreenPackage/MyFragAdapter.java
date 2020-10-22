package com.example.zeeshblogapp.WelcomeScreenPackage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragAdapter extends FragmentPagerAdapter {

    public MyFragAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0 : fragment = new FirstFrag();
            break;
            case 1 : fragment = new SecondFrag();
            break;
            case 2 : fragment = new ThirdFrag();
            break;
            default : fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
