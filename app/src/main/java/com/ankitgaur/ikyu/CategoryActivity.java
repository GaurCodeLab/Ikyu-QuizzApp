package com.ankitgaur.ikyu;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryActivity extends AppCompatActivity {

    private String mDisplayName;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setupDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


        ImageButton GK;
        ImageButton computer_science;
        ImageButton Aptitude;
        ImageButton History;

        GK= findViewById(R.id.GK);
        computer_science= findViewById(R.id.compture_science);
        Aptitude=findViewById(R.id.aptitude);
        History= findViewById(R.id.history);



    }

    private void setupDisplayName(){

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);

        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);

        if (mDisplayName == null) mDisplayName = "Anonymous";
    }



}
