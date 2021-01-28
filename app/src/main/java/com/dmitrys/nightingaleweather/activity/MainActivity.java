package com.dmitrys.nightingaleweather.activity;

import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.Locale;

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
        Locale locale = null;
        setTitle(R.string.app_name);
        switch (item.getItemId()) {
            case R.id.menu_rus:
                locale = new Locale("ru");
                break;
            case R.id.menu_eng:
                locale = new Locale("en");
                break;
            case R.id.menu_fra:
                locale = new Locale("fr");
                break;
        }
        // display the text of another locale
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);

        // recreates activity
        recreate();
        return super.onOptionsItemSelected(item);
    }

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
            String pressAgain = getString(R.string.press_again);
            Toast.makeText(getBaseContext(), pressAgain, Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    public static DBCityHelper getDBHelper() {
        return DBHelper;
    }
}