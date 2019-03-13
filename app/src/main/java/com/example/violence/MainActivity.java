package com.example.violence;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;

import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;


public class MainActivity extends Activity
        implements View.OnClickListener,  NavigationView.OnNavigationItemSelectedListener {

    /* intro */
    private static final int REQUEST_CODE = 1234;
    private SharedPreferences prefs;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /* intro */

    ImageView aboutBtn, helpBtn, contactBtn, fehrestBtn;
    int permissionCheck;
    MediaPlayer mediaPlayer;
    ImageView soundOn, soundOff, menu;
//    SharedPreferences prefs;
    DrawerLayout drawer;
    AudioManager audioManager;
    boolean musicPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

            /* intro */
        prefs = getSharedPreferences("your.pckage.name", MODE_PRIVATE);

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            loadTutorial();
            prefs.edit().putBoolean("firstrun", false).apply();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            /* intro */



        // init
        aboutBtn = (ImageView) findViewById(R.id.about_btn);
        helpBtn = (ImageView) findViewById(R.id.help_btn);
        contactBtn = (ImageView) findViewById(R.id.contact_btn);
        fehrestBtn = (ImageView) findViewById(R.id.fehrestBtn);
        permissionCheck  = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        soundOn = findViewById(R.id.soundOn);
        soundOff = findViewById(R.id.soundOff);
        menu = (ImageView) findViewById(R.id.menu);
        mediaPlayer = MediaPlayer.create(this, R.raw.main);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//        soundOn.setAlpha(1f);
//        soundOff.setAlpha(0f);


        // onClick
        aboutBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        fehrestBtn.setOnClickListener(this);
        soundOn.setOnClickListener(this);
        contactBtn.setOnClickListener(this);
        menu.setOnClickListener(this);

        //drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    // onClick
    // home
    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){

            case R.id.menu:
                drawer.openDrawer(GravityCompat.START);
                break;

            case R.id.fehrestBtn:
                startActivity(new Intent(MainActivity.this, LessonsListActivity .class));
                break;

            case  R.id.help_btn:
                prefs.edit().putBoolean("firstrun", true).apply();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                break;

            case  R.id.about_btn:
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                break;

            case  R.id.contact_btn:
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
                break;

//            case R.id.soundOn:
//                soundOnOff(soundOn);
//                break;
        }

    }


    // do sth whenever MainActivity is open
    // play music
    @Override
    protected void onResume() {
        super.onResume();
        if(!musicPaused){
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        } else {
            mediaPlayer.pause();
        }

    }

    // do sth whenever MainActivity is closed
    // stop music
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }


    /*       drawer       */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    // drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.barcode) {
            startActivity(new Intent(MainActivity.this, LessonsListActivity.class));

        } else if (id == R.id.game) {
            startActivity(new Intent(MainActivity.this, MainMenuScreen.class));

        } else if (id == R.id.about_heading) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

        } else if (id == R.id.exit) {
            showAlertDialogC("دلت میاد؟؟؟");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public  void soundOnOff( View imageView){


                    if (soundOn.getAlpha() == 1f){
                        mediaPlayer.pause();
                        musicPaused = true;
                    } else {
//                        float curVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//                        mediaPlayer.setVolume(curVolume, curVolume );
                        mediaPlayer.start();
                        musicPaused = false;
                        mediaPlayer.setLooping(true);
                    }

        soundOn.setAlpha(1f-soundOn.getAlpha()); //1-1=0
        soundOff.setAlpha(1f-soundOff.getAlpha()); //1-0=1

    }

    // Dialog correct answer
    protected void showAlertDialogC(String string){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage(string)
                .setPositiveButton("نه! هنوز هستم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton("زودی بر میگردم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
//                        finish();
                    }
                });

        builder.show();
    }

// double back pressed
    boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // go to FirstActivity
//            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("EXIT", true);
//            startActivity(intent);
//            super.onBackPressed();
//            return;
//            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        MDToast mdToast = MDToast.makeText(this,"برا خروج دوباره دکمه بازگشت را لمس کنید"
                ,MDToast.LENGTH_SHORT,MDToast.TYPE_INFO);
        mdToast.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


        /* intro */
        public void loadTutorial() {
            Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
            mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
            startActivityForResult(mainAct, REQUEST_CODE);

        }

    private ArrayList<TutorialItem> getTutorialItems(Context context) {

        TutorialItem tutorialItem1 = new TutorialItem(R.string.slide1_title, R.string.slide1_subtitle,
                R.color.slide1, R.drawable.b1, R.drawable.tut_page_3_background);

        TutorialItem tutorialItem2 = new TutorialItem(R.string.slide2_title, R.string.slide2_subtitle,
                R.color.slide2, R.drawable.b2, R.drawable.tut_page_3_background);

        TutorialItem tutorialItem3 = new TutorialItem(R.string.slide3_title, R.string.slide3_subtitle,
                R.color.slide3, R.drawable.b3, R.drawable.tut_page_3_background);

        TutorialItem tutorialItem4 = new TutorialItem(R.string.slide4_title, R.string.slide4_subtitle,
                R.color.slide4, R.drawable.b4, R.drawable.tut_page_3_background);

        TutorialItem tutorialItem5 = new TutorialItem(R.string.slide4_title, R.string.slide5_subtitle,
                R.color.slide5, R.drawable.b5, R.drawable.tut_page_3_background);


        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);
        tutorialItems.add(tutorialItem5);

        return tutorialItems;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //    super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            Toast.makeText(this, "Tutorial finished", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.violence/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.violence/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // double back pressed
//    boolean doubleBackToExitPressedOnce = false;

    public void click (View view){
        onBackPressed();
    }
        /* intro */


}
