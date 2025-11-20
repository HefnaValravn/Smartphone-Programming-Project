package com.example.smartphoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.snackbar.Snackbar;
import com.example.smartphoneproject.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    //for greeting message
    private static boolean greetedThisLaunch = false;

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

        //greet user only if they just opened the app
        if (!greetedThisLaunch) {
            Snackbar.make(findViewById(android.R.id.content),
                            "Hi " + nameValue + ", welcome back!",
                            Snackbar.LENGTH_SHORT)
                    .show();

            greetedThisLaunch = true;
        }

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

    public void toggleVisibility(View v){
        ImageView ivMain = findViewById(R.id.ivMain);
        ImageView ivSecond = findViewById(R.id.ivSecond);
        TextView tv = findViewById(R.id.tvInfo);

        if (ivMain.getVisibility() == View.INVISIBLE){
            ivSecond.setVisibility(View.INVISIBLE);
            ivMain.setVisibility(View.VISIBLE);
            tv.setText("I'm a Computer Science student from Spain, and this is my app!");
        }
        else if (ivMain.getVisibility() == View.VISIBLE){
            ivSecond.setVisibility(View.VISIBLE);
            ivMain.setVisibility(View.INVISIBLE);
            tv.setText("Why would you do that? :(");
        }
    }
}
