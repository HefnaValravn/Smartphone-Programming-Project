package com.example.smartphoneproject;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int savedThemeId = prefs.getInt("theme_radio_id", R.id.rbSystem);

        int themeMode = (savedThemeId == R.id.rbLight) ? AppCompatDelegate.MODE_NIGHT_NO
                : (savedThemeId == R.id.rbDark) ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}