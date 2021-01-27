package com.dmitrys.nightingaleweather.activity;

import android.content.Intent;
import android.os.Bundle;

import com.dmitrys.nightingaleweather.DBCity;
import com.dmitrys.nightingaleweather.DBCityHelper;
import com.dmitrys.nightingaleweather.R;
import com.dmitrys.nightingaleweather.helper.SQLiteHandler;
import com.dmitrys.nightingaleweather.helper.SessionManager;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dmitrys.nightingaleweather.activity.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;
    private SQLiteHandler db;
    private static long back_pressed;
    private static DBCityHelper DBHelper;
    private static DBCity dbCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        session = new SessionManager(getApplicationContext());

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        DBHelper = new DBCityHelper(this);
        dbCity = new DBCity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rus:
                break;
            case R.id.menu_eng:
                break;
            case R.id.menu_fra:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void changeCity(String city){
//        Fragment1 wf = (Fragment1) getSupportFragmentManager()
//                .findFragmentById(R.id.container);
//        wf.changeCity(city);
//        new CityPreference(this).setCity(city);
//    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUser();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            logoutUser();
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    public static DBCityHelper getDBHelper() {
        return DBHelper;
    }

    public static DBCity getDBCity() {
        return dbCity;
    }
}