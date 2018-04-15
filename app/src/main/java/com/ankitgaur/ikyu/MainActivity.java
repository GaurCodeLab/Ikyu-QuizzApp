package com.ankitgaur.ikyu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        private Button Begin;
        private ImageButton exit;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Begin= findViewById(R.id.StartButton);
        exit= findViewById(R.id.Exit_Button);
        Begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, LogInActivity.class);
                startActivity(myIntent);

            }
        });


    }
    @Override
    public void onBackPressed(){
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }




    public void onClickexit(View view) {
        finishAffinity();
    }
}
