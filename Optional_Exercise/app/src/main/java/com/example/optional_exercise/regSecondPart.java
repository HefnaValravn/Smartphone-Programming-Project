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

public class regSecondPart extends AppCompatActivity {
    private String varFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg_second_part);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent in = getIntent();
        varFirst = in.getStringExtra(MainActivity.EXTRA_FIRST_NAME);

    }

    public void submitLastName(View v){
        Intent in = new Intent(this, resultsActivity.class);
        EditText et = findViewById(R.id.etLastName);
        String LastName = et.getText().toString();

        in.putExtra(MainActivity.EXTRA_FIRST_NAME, varFirst);
        in.putExtra(MainActivity.EXTRA_LAST_NAME, LastName);

        startActivity(in);
    }
}