package com.example.app4c;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

    public void processOrder(View v){
        CheckBox cb1 = findViewById(R.id.cbPizza);
        CheckBox cb2 = findViewById(R.id.cbBurger);
        CheckBox cb3 = findViewById(R.id.cbSalad);
        TextView tv = findViewById(R.id.tvOrder);

        tv.setText("Your order is:");

        if (cb1.isChecked()){
            tv.append("\nPizza");
        }
        if (cb2.isChecked()){
            tv.append("\nBurger");
        }
        if (cb3.isChecked()){
            tv.append("\nSalad");
        }
    }
}