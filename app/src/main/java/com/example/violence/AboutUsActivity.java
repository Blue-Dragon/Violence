package com.example.violence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    float currSize = 18;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // back button
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        t1 = findViewById(R.id.us1);
        t2 = findViewById(R.id.us2);
        t3= findViewById(R.id.us3);
        t4= findViewById(R.id.us4);
        t5= findViewById(R.id.us5);
        t6= findViewById(R.id.us6);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // on back button pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }


    public  void onClick(View v){
        int id = v.getId();

        switch (id) {
            case R.id.mag_more_A:
                magMore();
                Toast.makeText(this,  "سایز فونت: " + (int) t2.getTextSize(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.mag_less_a:
                magLess();
                Toast.makeText(this,  "سایز فونت: " + (int) t2.getTextSize(), Toast.LENGTH_SHORT).show();
                break;

            default:
//                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;
        }

    }


    void magMore(){
        float nextSize = currSize+=2f;
        t1.setTextSize(nextSize);
        t2.setTextSize(nextSize);
        t3.setTextSize(nextSize);
        t4.setTextSize(nextSize);
        t5.setTextSize(nextSize);
        t6.setTextSize(nextSize);

        size = (int) t2.getTextSize();

    }

    void magLess(){
        float nextSize = currSize-=2f;
        if(t2.getTextSize()>=2){
            t1.setTextSize(nextSize);
            t2.setTextSize(nextSize);
            t3.setTextSize(nextSize);
            t4.setTextSize(nextSize);
            t5.setTextSize(nextSize);
            t6.setTextSize(nextSize);

            size = (int) t2.getTextSize();

        } else {
            Toast.makeText(this,  "کوچک تر از این سایز ممکن نیست! " , Toast.LENGTH_SHORT).show();
        }

    }

}
