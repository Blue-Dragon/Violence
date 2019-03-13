package com.example.violence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first);



        //exit of start
        if (getIntent().getBooleanExtra("EXIT", false) ) {
            finish();
        } else {
            //start
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            // set an animation
            overridePendingTransition(R.anim.blink_in, R.anim.blink_out);
        }

    }
}
