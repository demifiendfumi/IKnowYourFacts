package com.myapplicationdev.android.iknowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button btnReadLater;
    AlarmManager am;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager)findViewById(R.id.viewpager1);

        btnReadLater = (Button)findViewById(R.id. btnReadLater);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());
        al.add(new Frag4());
        al.add(new Frag5());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 300);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this, reqCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("index", vPager.getCurrentItem());
        editor.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int resume = sharedpreferences.getInt("index",0);
        vPager.setCurrentItem(resume, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_previous){
            if (vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem()-1;
                vPager.setCurrentItem(previousPage, true);
            }
            return true;
        }else if(id == R.id.action_random){
            Random randomNo = new Random();
            int randomPage = randomNo.nextInt(vPager.getChildCount());
            Log.d("random", String.valueOf(randomNo.nextInt(vPager.getChildCount())));
            vPager.setCurrentItem(randomPage, true);


            return true;
        }else if(id == R.id.action_next){
            int max = vPager.getChildCount();
            if(vPager.getCurrentItem() < max-1){
                int nextPage = vPager.getCurrentItem()+1;
                vPager.setCurrentItem(nextPage, true);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
