package com.example.zeeshblogapp.WelcomeScreenPackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zeeshblogapp.R;

public class ThirdFrag extends Fragment {

    TextView thirdFragButton;
    ThirdFragInterface thirdFragInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thirdFragButton = view.findViewById(R.id.thirdFrag_textViewButtonID);

        thirdFragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdFragInterface.startButton_Frag();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        thirdFragInterface = (ThirdFragInterface) context;
    }

    interface ThirdFragInterface{
        void startButton_Frag();
    }
}