package com.example.smartphoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.snackbar.Snackbar;
import com.example.smartphoneproject.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setting theme
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int savedThemeId = prefs.getInt("theme_radio_id", R.id.rbSystem);

        if (savedThemeId == R.id.rbLight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (savedThemeId == R.id.rbDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }


        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, MapSelectionActivity.class);
                startActivity(in);
            }
        });

        Intent in = getIntent();

        //persistent name retrieval
        String nameValue = in.getStringExtra(FirstActivity.NAME);
        if (nameValue == null){
            nameValue = prefs.getString("user_name", "");
        }
        TextView tv = findViewById(R.id.tvWelcome);
        tv.setText("Hello " + nameValue + "!\nWe are very happy to see you.");

        //music state retrieval
        boolean musicOn = prefs.getBoolean("music_on", false);

        //start music service
        if (musicOn) {
            startService(new Intent(this, MusicService.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    //toolbar option selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_browser){
            Intent in = new Intent(this, BrowserActivity.class);
            startActivity(in);
            return true;
        }

        else if (id == R.id.action_settings){
            Intent in = new Intent(this, SettingsActivity.class);
            startActivity(in);
            return true;
        }

        else if (id == R.id.action_about){
            Intent in = new Intent(this, AboutActivity.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
