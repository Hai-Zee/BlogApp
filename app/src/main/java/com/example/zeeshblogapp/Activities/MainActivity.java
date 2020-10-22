package com.example.zeeshblogapp.Activities;

//SHA-1 certificate fingerprint:
//    E7:C7:0A:5A:8E:76:50:35:9B:14:E3:C6:90:FD:E5:A7:91:77:77:40

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zeeshblogapp.MyInterface;
import com.example.zeeshblogapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import MyAdapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

        FloatingActionButton floatingActionButton;
        //recyclerView declaration
        RecyclerView recyclerView;
        MyAdapter myAdapter;
        List<Data> list = new ArrayList<Data>();
        // Firebase declaration
        FirebaseAuth myAuth;
        FirebaseDatabase retrievMyDatabase;
        DatabaseReference retrieveRef;
        Data deletedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        clickListeners();
        setUpRecyclerView();
        setUpFirebase();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    switch (direction){
                        case ItemTouchHelper.LEFT :
                            final int pos = viewHolder.getAdapterPosition();
                            deletedItem = list.get(pos);
                            list.remove(pos);
                            myAdapter.notifyDataSetChanged();
                            Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                         list.add(pos, deletedItem);
                                         myAdapter.notifyDataSetChanged();
                                }
                            }).show();
                    }
            }
        };
           ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
           itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        retrieveRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //to clear the list, so that there will not be any duplicate data in app, becoz list m purana data  pda rehta h
                list.clear();
                // Retrieving the data
                for(DataSnapshot mySnapshot : dataSnapshot.getChildren()){

                   Data myData =  mySnapshot.getValue(Data.class);
                   list.add(myData);
                    Log.d("rtrv", "onDataChange: " + myData.getTrip());
                }
                setUpRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickListeners() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UploadPost.class));
                finish();
            }
        });
    }

    private void setUpFirebase() {
        myAuth = FirebaseAuth.getInstance();
        retrievMyDatabase = FirebaseDatabase.getInstance();
        retrieveRef = retrievMyDatabase.getReference().child("social_media_integration_spark");
    }

    private void setUpRecyclerView() {
        // setUpRecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyInterface myInterface = new MyInterface() {
            @Override
            public void onClickInterface(Data data) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("trip", data.getTrip());
                intent.putExtra("image", data.getImage());
                intent.putExtra("story", data.getStory());
                Log.d("balti", data.getStory());
                startActivity(intent);
                finish();
            }
        };
        myAdapter = new MyAdapter(list, myInterface);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recyclerViewID);
        floatingActionButton = findViewById(R.id.floatingActionButtonID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        to create three dot menu option in action bar
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
//                        myAuth.signOut(); startActivity(new Intent(this, LoginPage.class));
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        AlertDialog.Builder myBuilder = new AlertDialog.Builder(this)
                .setTitle("SignOut")
                .setMessage("Are you sure ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myAuth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginPage.class));
                        dialog.dismiss();
                    }
                });
                AlertDialog myAlert = myBuilder.create();
//        to select given option and add functionality to them
        switch (item.getItemId()) {
            case R.id.logoutMenuID : {
                myAlert.show();
                break;
            }
            case R.id.profileMenuID : Toast.makeText(this, "Balti", Toast.LENGTH_SHORT).show();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}