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

public class FirstFrag extends Fragment {

    ImageView frag0_rightArrowButton;
    FirstFragInterface firstFragInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frag0_rightArrowButton = view.findViewById(R.id.frag0_rightArrowImageID);
        frag0_rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragInterface.firstFragRightArrow();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        firstFragInterface = (FirstFragInterface) context;
    }

    interface FirstFragInterface{
        public void firstFragRightArrow();
    }
}