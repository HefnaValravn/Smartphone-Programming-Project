package com.example.smartphoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


import com.example.smartphoneproject.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {


    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting stored settings from storage
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        //retrieve and set theme
        int savedThemeId = prefs.getInt("theme_radio_id", R.id.rbSystem); // default system theme
        applyTheme(savedThemeId);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Settings");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //put this section in onCreate instead of a separate function
        //because I need the state of the switch to be tracked when the user
        //exits the settings activity
        TextView tv = findViewById(R.id.tvMusic);
        Switch sw = findViewById(R.id.swMusic);
        SeekBar sb = findViewById(R.id.sbVolume);
        RadioGroup radioGroup = findViewById(R.id.rgTheme);


        boolean musicOn = prefs.getBoolean("music_on", false);

        //setting switch and text based on storage music state
        sw.setChecked(musicOn);
        tv.setText(musicOn ? "Music is ON" : "Music is OFF");

        //start music if necessary (redundant as it already starts in HomeActivity, but useful nonetheless)
        if (musicOn) {
            startService(new Intent(this, MusicService.class));
        }

        //listener for switch
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tv.setText(isChecked ? "Music is ON" : "Music is OFF");

            //change storage music state
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("music_on", isChecked);
            editor.apply();

            if (sw.isChecked()) {
                tv.setText("Music is ON");
                startService(new Intent(this, MusicService.class));
            } else {
                tv.setText("Music is OFF");
                stopService(new Intent(this, MusicService.class));
            }
        });

        //listener for seekBar
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int max = seekBar.getMax();
                //log of loudness (max - progress) divided by log of max to normalize it [0-1]
                float volume = 1 - (float)(Math.log(max - progress) / Math.log(max));
                MusicService.setVolume(volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        radioGroup.check(savedThemeId);
        //listener for RadioGroup
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            applyTheme(checkedId); // apply theme immediately
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("theme_radio_id", checkedId); // save selection
            editor.apply();
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //helper method to change theme
    private void applyTheme(int checkedId) {
        if (checkedId == R.id.rbLight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (checkedId == R.id.rbDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

}