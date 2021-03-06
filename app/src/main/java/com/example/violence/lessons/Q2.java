package com.example.violence.lessons;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.violence.MainActivity;
import com.example.violence.R;
import com.valdesekamdem.library.mdtoast.MDToast;


public class Q2 extends AppCompatActivity {


    TextView tv1, tv2, tv3, tv4;
    boolean clickable = true;
    MediaPlayer mPlayerC, mPlayerW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);

        tv1 = findViewById(R.id.tv21);
        tv2 = findViewById(R.id.tv22);
        tv3 = findViewById(R.id.tv23);
        tv4 =  findViewById(R.id.tv24);
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

            if (view.getId() == R.id.tv21){
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
                .setPositiveButton("سوال بعد", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =  new Intent(getApplication(), Q3.class);
                        // set an animation
                        overridePendingTransition(R.anim.blink_in, R.anim.blink_out);
                        startActivity(intent);
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
