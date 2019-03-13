package com.example.violence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.violence.lessons.A1;
import com.example.violence.lessons.A2;
import com.example.violence.lessons.A3;
import com.example.violence.lessons.A4;
import com.example.violence.lessons.A5;
import com.example.violence.lessons.A6;
import com.example.violence.lessons.A7;
import com.example.violence.lessons.A8;
import com.example.violence.lessons.Q1;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;


public class LessonsListActivity extends AppCompatActivity {


    List<lessonsList> contacts;
    ListView listView;
    LessonsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_list);

        listView = (ListView) findViewById(R.id.listview);
        contacts = new ArrayList<>();
        prepareData();
        refreshDisplay();

        // back button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    private void prepareData() {
        contacts.add(new lessonsList("مقدمه", R.drawable.a1));
        contacts.add(new lessonsList("خشونت چیست؟", R.drawable.a2));
        contacts.add(new lessonsList("انواع خشونت", R.drawable.a3));

        contacts.add(new lessonsList("دلایل ایجاد خشونت", R.drawable.a4));
        contacts.add(new lessonsList("عواقب خشونت و آثار آن", R.drawable.a5));
        contacts.add(new lessonsList("درمان خشونت", R.drawable.a6));

        contacts.add(new lessonsList("پیشگیری از خشونت و نکات تکمیلی", R.drawable.a7));
        contacts.add(new lessonsList("منابع", R.drawable.a8));
        contacts.add(new lessonsList("آزمون", R.drawable.quiz));

    }


    private void refreshDisplay() {
        adapter = new LessonsListAdapter(this, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        startActivity(new Intent(getApplicationContext(), A1.class));
                        break;

                    case 1:
                        startActivity(new Intent(getApplicationContext(), A2.class));
                        break;

                    case 2:
                        startActivity(new Intent(getApplicationContext(), A3.class));
                        break;

                    case 3:
                        startActivity(new Intent(getApplicationContext(), A4.class));
                        break;

                    case 4:
                        startActivity(new Intent(getApplicationContext(), A5.class));
                        break;

                    case 5:
                        startActivity(new Intent(getApplicationContext(), A6.class));
                        break;

                    case 6:
                        startActivity(new Intent(getApplicationContext(), A7.class));
                        break;

                    case 7:
                        startActivity(new Intent(getApplicationContext(), A8.class));
                        break;

                    case 8:
                        startActivity(new Intent(getApplicationContext(), Q1.class));
                        break;

                        default:
                            MDToast mdToast = MDToast.makeText(getApplicationContext(),"این صفحه وجود ندارد!"
                                    ,MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
                            mdToast.show();

                }

            }
        });

    }

    // colored toast
    public void sayNot(String s) {
        MDToast mdToast = MDToast.makeText(this,s
                ,MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
        mdToast.show();
    }
    public void say(String s) {
        MDToast mdToast = MDToast.makeText(this, s
                , MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
        mdToast.show();
    }

    // on back button pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}
