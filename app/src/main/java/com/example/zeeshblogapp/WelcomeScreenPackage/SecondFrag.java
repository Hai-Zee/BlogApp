package com.example.zeeshblogapp.WelcomeScreenPackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zeeshblogapp.R;

public class SecondFrag extends Fragment {

    ImageView frag1_rightArrowButton, frag1_leftArrowButton;
    SecondFragInterface secondFragInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frag1_rightArrowButton = view.findViewById(R.id.frag1_rightArrowImageID);
        frag1_leftArrowButton = view.findViewById(R.id.frag1_leftArrowImageID);

        frag1_leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragInterface.secFragLeftArrow();
            }
        });

        frag1_rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragInterface.secFragRightArrow();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        secondFragInterface = (SecondFragInterface) context;
    }

    interface SecondFragInterface{
        void secFragRightArrow();
        void secFragLeftArrow();
    }
}