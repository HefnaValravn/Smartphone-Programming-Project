package com.example.app2;

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
    public static final String USERNAME = "";

    //ignore this method
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

    //method used by the button in the Main View when you click on it
    public void submitName(View v){
        //create a "connection" between this activity and the target one
        Intent in = new Intent(this, SecondActivity.class);
        //get the EditText field from the main view by its id (using R!!!!)
        EditText et = findViewById(R.id.etName);
        //extract text from field
        String name = et.getText().toString();
        //putExtra assigns the value of "name" to a constant
        // that is now available BETWEEN VIEWS/.java FILES.
        in.putExtra(USERNAME, name);

        //jump to second view
        startActivity(in);
    }
}