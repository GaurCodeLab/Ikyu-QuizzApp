package com.ankitgaur.ikyu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ImageButton GK;
        ImageButton computer_science;
        ImageButton Aptitude;
        ImageButton History;

        GK= findViewById(R.id.GK);
        computer_science= findViewById(R.id.compture_science);
        Aptitude=findViewById(R.id.aptitude);
        History= findViewById(R.id.history);

    }
}
