package com.example.zeeshblogapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshblogapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountPage extends AppCompatActivity {
        FirebaseAuth myAuth;
        FirebaseUser myUser;
        TextInputLayout createEmail, createPassword;
        Button signUp;
        TextView signInTextButton;
        ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        createEmail = findViewById(R.id.signUpEmailID);
        createPassword = findViewById(R.id.signUpPasswordID);
        signUp = findViewById(R.id.signUpButtonID);
        signInTextButton = findViewById(R.id.alreadySignInTextButtonID);
        progressBar = findViewById(R.id.progressBarID);
        progressBar.setVisibility(View.GONE);

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String createEmailUser = createEmail.getEditText().getText().toString().trim();
                String createPasswordUser = createPassword.getEditText().getText().toString().trim();

                if (!createEmail.equals(null) && !createPassword.equals(null)) {
                    progressBar.setVisibility(View.VISIBLE);
                    createEmail.setError(null);
                    createPassword.setError(null);
                    myAuth.createUserWithEmailAndPassword(createEmailUser, createPasswordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            Boolean result = task.isSuccessful();
//                            Log.d("Check here..", result.toString());
                            if (task.isSuccessful()) {
                                startActivity(new Intent(CreateAccountPage.this, MainActivity.class));
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();
                            }else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(CreateAccountPage.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else{
                    if(createEmail.equals(null) && !createPassword.equals(null)){
                        createEmail.setError("required");
                        createPassword.setError(null);
                    }
                    else if (!createEmail.equals(null) && createPassword.equals(null)){
                        createPassword.setError("required");
                        createEmail.setError(null);
                    }
                    else if(createEmail.equals(null) && createPassword.equals(null)){
                        createEmail.setError("required");
                        createPassword.setError("required");
                    }
                }
            }
        });

            signInTextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CreateAccountPage.this, LoginPage.class));
                }
            });
    }
}