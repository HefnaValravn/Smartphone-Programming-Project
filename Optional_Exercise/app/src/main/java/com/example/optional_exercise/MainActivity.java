package com.example.optional_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_FIRST_NAME = "com.example.optional_exercise.FIRST_NAME";
    public static final String EXTRA_LAST_NAME  = "com.example.optional_exercise.LAST_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submitFirstName(View v){
        Intent in = new Intent(this, regSecondPart.class);
        EditText et = findViewById(R.id.etFirstName);
        String name = et.getText().toString();
        in.putExtra(EXTRA_FIRST_NAME, name);

        startActivity(in);
    }
}