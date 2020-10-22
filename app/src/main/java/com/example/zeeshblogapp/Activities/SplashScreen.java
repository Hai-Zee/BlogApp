package com.example.zeeshblogapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.zeeshblogapp.Activities.MainActivity;
import com.example.zeeshblogapp.WelcomeScreenPackage.WelcomeScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth myAuth;
//    FirebaseAuth.AuthStateListener myAuthStateListener;
    FirebaseUser myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        if (myUser!=null) {
            startActivity(new Intent(this, MainActivity.class));
          }
        else{
            startActivity(new Intent(this, WelcomeScreen.class));
        }
        finish();
    }

}