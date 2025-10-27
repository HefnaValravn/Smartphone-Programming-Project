package com.example.app4b;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
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

    public void processResponse(View v){
        RadioGroup group = findViewById(R.id.rgOptions);

        /* do NOT do this
        RadioButton r1 = findViewById(R.id.rbYes);
        RadioButton r2 = findViewById(R.id.rbNo);
        RadioButton r3= findViewById(R.id.rbMaybe);
         */

        TextView tv = findViewById(R.id.tvResponse);

        int selection = group.getCheckedRadioButtonId();

        if (selection == R.id.rbYes){
            tv.setText("Great, we will see you there!");
        }
        else if (selection == R.id.rbNo){
            tv.setText("We are sorry to hear that");
        }
        else if (selection == R.id.rbMaybe){
            tv.setText("Hopefully you will find the time to join us");
        }
        else{
            tv.setText("Are you blind??? You must select one!");
        }
    }
}