package com.example.violence.lessons;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.violence.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class A2 extends AppCompatActivity implements View.OnClickListener {

    ImageView btnPlay, btnPause;
    MediaPlayer mplayer;
    SeekBar scrubbar;
    AudioManager audioManager;

    ImageView magMore, magLess;
    TextView tv;
    float currSize;

    static Time time;
    static TextView timerTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        magMore = findViewById(R.id.mag_more2);
        magLess = findViewById(R.id.mag_less2);
        tv = findViewById(R.id.tv_a2);

        currSize = 18f;

        /***************    audio    ***************/
        btnPause =  findViewById(R.id.pause_btn);
        btnPlay = findViewById(R.id.start_btn);
        scrubbar = (SeekBar) findViewById(R.id.seekBar3);
        timerTv = findViewById(R.id.timer);
        // buttons
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        // player
        mplayer = MediaPlayer.create(this, R.raw.a2);
        // audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//        //position
        scrubbar.setMax(mplayer.getDuration());
        scrubbar.setProgress(0);

        /******************************** time ************************************/
        final int musicTotalTime = (mplayer.getDuration()/1000);
        time = new Time(0,0,0);
        time.setSeconds(musicTotalTime);
        timerTv.setText(String.valueOf(time));
        /******************************** time ************************************/

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubbar.setProgress(mplayer.getCurrentPosition());
            }
        }, 0, 100);
        scrubbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mplayer != null && fromUser){
                    mplayer.seekTo(progress);
                }

                time.setSeconds((mplayer.getCurrentPosition()/1000));
                timerTv.setText( String.valueOf(time));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /***************    audio    ***************/
    }

    private void showResmainingTime() {
//        int musicCurrentTime = (mplayer.getCurrentPosition())/1000;
//        time.getTime();
//        timerTv.setText(String.valueOf(time));
    }


    public  void onClick(View v){
        int id = v.getId();

        switch (id) {
            case R.id.mag_more2:
                magMore();
                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;

            case R.id.mag_less2:
                magLess();
                Toast.makeText(this,  "سایز فونت: " + (int) currSize, Toast.LENGTH_SHORT).show();
                break;

            case R.id.pause_btn:

                btnPlay.animate().alpha(1-btnPlay.getAlpha()).setDuration(500); //now btnPlay.getAlpha() = 0;
                btnPause.animate().alpha(1-btnPause.getAlpha()).setDuration(500); //now btnPause.getAlpha() = 1

                if(btnPlay.getAlpha()==0){
                    mplayer.pause();
                } else  {
                    mplayer.start();
                    showResmainingTime();
                }

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mplayer.stop();
        finish();
    }
}
