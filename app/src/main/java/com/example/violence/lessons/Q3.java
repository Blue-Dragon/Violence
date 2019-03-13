package com.example.violence.lessons;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.violence.MainActivity;
import com.example.violence.R;
import com.valdesekamdem.library.mdtoast.MDToast;


public class Q3 extends AppCompatActivity {

    ImageView imageView;
    TextView tv1, tv2, tv3, tv4;
    boolean clickable = true;
    MediaPlayer mPlayerC, mPlayerW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);

        tv1 = findViewById(R.id.tv31);
        tv2 = findViewById(R.id.tv32);
        tv3 = findViewById(R.id.tv33);
        tv4 =  findViewById(R.id.tv34);
        mPlayerC = MediaPlayer.create(this, R.raw.correct_answer);
        mPlayerW = MediaPlayer.create(this, R.raw.wrong_answer);


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


    public void answerClick (View view){
        if (clickable){

            if (view.getId() == R.id.tv34){
                view.setBackgroundResource( R.drawable.correct);
                mPlayerC.start();
                clickable = false;
                showAlertDialog("آفرین... درسته!");
                Q1.correctAcnswer++;
            }
            else {
                view.setBackgroundResource( R.drawable.wrong);
                mPlayerW.start();
                clickable = false;
                showAlertDialog("ای داد! پس حواست کجاس؟");
            }
        }
    }

    // Dialog  answer
    protected void showAlertDialog(String string){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage(string)
                .setCancelable(false)
                .setNeutralButton("دیدن نتیجه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showResultDialog(" نتیجه آزمون");
//                        finish();
                    }
                });
        builder.show();
    }

    protected void showResultDialog(String string){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String result = String.valueOf(Q1.correctAcnswer);

        builder
                .setMessage(string)
                .setCancelable(false)
                .setMessage("تعداد پاسخ های صحیح: " + result + " (از 3 سوال)")
                .setNeutralButton("بازگشت به فهرست", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Q1.correctAcnswer = 0;

                        finish();
                    }
                });
        builder.show();
    }


    @Override
    public void onBackPressed() {
        MDToast mdToast = MDToast.makeText(this," خروچ در حین انجام آزمون ممکن نیست!"
                ,MDToast.LENGTH_SHORT,MDToast.TYPE_INFO);
        mdToast.show();
    } //Do nothing

}
