package com.example.zeeshblogapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zeeshblogapp.R;

public class SecondActivity extends AppCompatActivity {

    ImageView secImage;
    TextView secTrip, secStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secTrip = findViewById(R.id.secActivityTripID);
        secStory = findViewById(R.id.secActivityStoryID);
        secImage = findViewById(R.id.secActivityImageID);

        Intent intent = getIntent();

        String trip = intent.getStringExtra("trip");
        String story = intent.getStringExtra("story");
        String imageUri = intent.getStringExtra("image");

        secTrip.setText(trip);
        secStory.setText(story);
        Glide.with(SecondActivity.this).load(imageUri).centerCrop().into(secImage);
    }
}