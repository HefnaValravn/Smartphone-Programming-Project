package com.example.app4a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void sendData(View v){
        EditText et1 = findViewById(R.id.etName);
        String name = et1.getText().toString();

        EditText et2 = findViewById(R.id.etAge);
        String age = et2.getText().toString();

        EditText et3 = findViewById(R.id.etEmail);
        String email = et3.getText().toString();

        EditText et4 = findViewById(R.id.etPass);
        String pass = et4.getText().toString();

        Intent in = new Intent(this, SecondActivity.class);

        //important part!
        Bundle info = new Bundle();
        info.putString("na", name);
        info.putString("ag", age);
        info.putString("em", email);
        info.putString("pa", pass);

        in.putExtras(info);

        startActivity(in);
    }
}