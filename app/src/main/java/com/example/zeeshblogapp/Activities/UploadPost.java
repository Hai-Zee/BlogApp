package com.example.zeeshblogapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zeeshblogapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Model.Data;

public class UploadPost extends AppCompatActivity {

    ImageView selectImageView;
    Button uploadButton;
    private static final int REQUEST_CODE = 1;
    Uri imageUri;
    TextInputLayout trip, story;
//    firebase task
//    private FirebaseDatabase myDatabase;
    private DatabaseReference myReference;
    private StorageReference myStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);

        selectImageView = findViewById(R.id.uploadImageID);
        uploadButton = findViewById(R.id.uploadButtonID);
        trip = findViewById(R.id.inputTripID);
        story = findViewById(R.id.inputStoryID);

//        myDatabase = FirebaseDatabase.getInstance();
        myReference = FirebaseDatabase.getInstance().getReference().child("MyBlog");
        myStorageReference = FirebaseStorage.getInstance().getReference();

        selectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), REQUEST_CODE);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tripValue = trip.getEditText().getText().toString().trim();
                final String storyValue = story.getEditText().getText().toString().trim();

//                !tripValue.equals(null) && !imageUri.equals(null) && !storyValue.equals(null)

               if(!TextUtils.isEmpty(tripValue) && !TextUtils.isEmpty(storyValue) && !TextUtils.isEmpty(imageUri.toString())){
                   // here will upload these three values on firebase

                   StorageReference filePath =  myStorageReference.child("My Blog Storage").child(imageUri.getLastPathSegment());
                   filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                           if(task.isSuccessful()){
                               task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                       String downloadedURL = uri.toString();

                                       Data myData = new Data(tripValue, storyValue, downloadedURL);

                                       DatabaseReference newReference = myReference.push();
                                       newReference.setValue(myData);
                                   }
                               });
                           }
                       }
                   });

                   startActivity(new Intent(UploadPost.this, MainActivity.class));
                   finish();
               }
               else
                   Toast.makeText(UploadPost.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

//        SharedPreferences shared = getSharedPreferences("msg", MODE_PRIVATE);
//        SharedPreferences.Editor editor = shared.edit();
//        editor.putString("key", "str");
//        editor.apply();
//
//        SharedPreferences get = getSharedPreferences("msg", MODE_PRIVATE);
//        String value = get.getString("key", "Yha likh bhai");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
//            image.setImageURI(imageUri);
            Glide.with(this).load(imageUri).centerCrop().into(selectImageView);
        }
    }
}