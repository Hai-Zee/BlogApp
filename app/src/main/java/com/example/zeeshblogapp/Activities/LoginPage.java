package com.example.zeeshblogapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class LoginPage extends AppCompatActivity {
    FirebaseAuth myAuth;
    FirebaseUser myUser;
    Button signInButton;
    TextView goSignUp;
    TextInputLayout signInEmail, signInPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        progressBar = findViewById(R.id.progressBarID);
        goSignUp = findViewById(R.id.goSignUpTextButtonID);
        signInEmail = findViewById(R.id.signInEmailID);
        signInPassword = findViewById(R.id.signInPasswordID);
        signInButton = findViewById(R.id.signInButtonID);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signInEmailFromUser = signInEmail.getEditText().getText().toString();
                String signInPasswordFromUser = signInPassword.getEditText().getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.setCancelable(false);

                if (!signInEmailFromUser.equals(null) && !signInPasswordFromUser.equals(null)) {
                    progressBar.setVisibility(View.VISIBLE);
                    signInEmail.setError(null);
                    signInPassword.setError(null);
                    myAuth.signInWithEmailAndPassword(signInEmailFromUser, signInPasswordFromUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginPage.this, "Signed In", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPage.this, MainActivity.class));
                                finish();

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginPage.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else{
                   if(signInEmailFromUser.equals(null) && !signInPasswordFromUser.equals(null)){
                       signInEmail.setError("required");
                       signInPassword.setError(null);
                   }
                   else if (!signInEmailFromUser.equals(null) && signInPasswordFromUser.equals(null)){
                       signInPassword.setError("required");
                       signInEmail.setError(null);
                   }
                   else if(signInEmailFromUser.equals(null) && signInPasswordFromUser.equals(null)){
                       signInEmail.setError("required");
                       signInPassword.setError("required");
                   }
                }
            }
        });

        goSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, CreateAccountPage.class));
                finish();
            }
        });
    }
}