package com.dmitrys.nightingaleweather;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {

    SharedPreferences sPrefs;

    public CityPreference(Activity activity){
        sPrefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    // Sydney as the default city
    public String getCity(){
        return sPrefs.getString("city", "Omsk, RU");
    }

    public void setCity(String city){
        sPrefs.edit().putString("city", city).commit();
    }

}
