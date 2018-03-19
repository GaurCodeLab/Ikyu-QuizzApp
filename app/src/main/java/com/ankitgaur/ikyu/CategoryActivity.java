package com.ankitgaur.ikyu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class CategoryActivity extends AppCompatActivity {

    private String mDisplayName;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setupDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        Button GK;
        Button computer_science;
        Button Aptitude;
        Button science;

        GK= (Button) findViewById(R.id.b2);
        computer_science= findViewById(R.id.b1);
        Aptitude=findViewById(R.id.b4);
       science= findViewById(R.id.b3);

        GK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(CategoryActivity.this, General_KNowledge_Activity.class);
                startActivity(myIntent);
            }
        });



    }

    private void setupDisplayName(){

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);

        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);

        if (mDisplayName == null) mDisplayName = "Anonymous";
    }





}
