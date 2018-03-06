package com.ankitgaur.ikyu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        private Button Begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Begin= findViewById(R.id.StartButton);
        Begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, LogInActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
