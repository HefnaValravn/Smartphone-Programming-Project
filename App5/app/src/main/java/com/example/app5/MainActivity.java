package com.example.app5;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    public void setMusic(View v){
        TextView tv = findViewById(R.id.tvMusic);
        Switch music = findViewById(R.id.swMusic);

        if (music.isChecked()){
            tv.setText("Music is ON");
        }
        else{
            tv.setText("Music is OFF");
        }
    }

    public void setRating(View v){
        TextView tv = findViewById(R.id.tvRating);
        RatingBar rb = findViewById(R.id.rbRating);
        tv.setText(rb.getNumStars());
    }

    public void setWebsite(View v){

    }
}