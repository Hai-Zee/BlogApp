package com.example.zeeshblogapp.WelcomeScreenPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.zeeshblogapp.Activities.LoginPage;
import com.example.zeeshblogapp.R;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class WelcomeScreen extends AppCompatActivity implements FirstFrag.FirstFragInterface, SecondFrag.SecondFragInterface, ThirdFrag.ThirdFragInterface{
        ViewPager viewPager;
        MyFragAdapter myFragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        viewPager = findViewById(R.id.viewPagerID);

        myFragAdapter = new MyFragAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(myFragAdapter);


    }

    @Override
    public void firstFragRightArrow() {
       viewPager.setCurrentItem(1);
    }

    @Override
    public void secFragRightArrow() {
        viewPager.setCurrentItem(2);
    }

    @Override
    public void secFragLeftArrow() {
        viewPager.setCurrentItem(0);
    }

    @Override
    public void startButton_Frag() {
        Intent intent  = new Intent(WelcomeScreen.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}