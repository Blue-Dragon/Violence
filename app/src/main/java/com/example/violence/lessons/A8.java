package com.example.violence.lessons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.violence.R;




public class A8 extends AppCompatActivity {

    ImageView magMore, magLess;
    TextView tv;
    float currSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8);

        magMore = findViewById(R.id.mag_more8);
        magLess = findViewById(R.id.mag_less8);
        tv = findViewById(R.id.tv_a8);
        currSize = 18f;

    }


    public  void onClick(View v){
        int id = v.getId();

        switch (id) {
            case R.id.mag_more8:
                magMore();
                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;

            case R.id.mag_less8:
                magLess();
                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;


            default:
//                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    void magMore(){
        tv.setTextSize(currSize+=2f);
    }

    void magLess(){
        if(tv.getTextSize()>=2){
            tv.setTextSize(currSize-=2f);
        } else {
            Toast.makeText(this,  "کوچک تر از این سایز ممکن نیست! " , Toast.LENGTH_SHORT).show();
        }

    }
}
