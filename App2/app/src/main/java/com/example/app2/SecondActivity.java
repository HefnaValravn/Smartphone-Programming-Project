package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //this code is ran within "onCreate" so that it gets ran on startup of the view
        //this part "captures" the connection from the previous view that created the connection
        Intent in = getIntent();
        //extract the string from the USERNAME variable,
        //which is accessible ACROSS VIEWS/.java FILES
        String nameValue = in.getStringExtra(MainActivity.USERNAME);
        //Get textView on this activity to put text into
        TextView tv = findViewById(R.id.tvMessage);
        //set text in this view with the value of the USERNAME constant
        tv.setText(String.format("Hello %s, welcome to App2", nameValue));
    }
}