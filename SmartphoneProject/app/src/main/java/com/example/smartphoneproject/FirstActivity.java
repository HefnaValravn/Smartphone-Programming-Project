package com.example.smartphoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {

    public static final String NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        String name = prefs.getString("user_name", null);

        if (name != null) {
            Intent in = new Intent(this, HomeActivity.class);
            startActivity(in);
            finish();
        }

    }

    public void processName(View v){
        Intent in = new Intent(this, HomeActivity.class);
        EditText et = findViewById(R.id.etName);
        String name = et.getText().toString();

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        prefs.edit().putString("user_name", name).apply();

        in.putExtra(NAME, name);

        startActivity(in);

    }
}