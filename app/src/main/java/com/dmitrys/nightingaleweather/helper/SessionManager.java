package com.dmitrys.nightingaleweather.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    Context context;

    // Shared preferences mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidLogin";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        sPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sPref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        // commit changes
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();

        Log.i(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return sPref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

}
