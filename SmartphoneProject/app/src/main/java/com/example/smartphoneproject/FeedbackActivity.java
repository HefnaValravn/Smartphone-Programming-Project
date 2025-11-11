package com.example.smartphoneproject;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setRating(View v){
        RatingBar rb = findViewById(R.id.rbRating);
        TextView tv = findViewById(R.id.tvRating);

        double rating = rb.getRating();

        tv.setText("Your current selection: " + rating + " stars");
    }


    public void setCheckboxes(View v){
        CheckBox cbDesign = findViewById(R.id.cbDesign);
        CheckBox cbFuncs = findViewById(R.id.cbFuncs);
        CheckBox cbNico = findViewById(R.id.cbNico);
        CheckBox cbNothing = findViewById(R.id.cbNothing);
        TextView tv = findViewById(R.id.tvCheckbox);

        tv.setText("Your current selection: ");

        // Determine which checkbox was clicked
        CheckBox clicked = (CheckBox) v;

        if (clicked == cbNothing && cbNothing.isChecked()) {
            // If "Nothing" is checked, uncheck all others
            cbDesign.setChecked(false);
            cbFuncs.setChecked(false);
            cbNico.setChecked(false);
        } else if (clicked != cbNothing && clicked.isChecked()) {
            // If any other is checked, uncheck "Nothing"
            cbNothing.setChecked(false);
        }

        if (cbDesign.isChecked()){
            tv.append("the design, ");
        }
        if (cbFuncs.isChecked()){
            tv.append("the functionalities, ");
        }
        if (cbNico.isChecked()){
            tv.append("Nico (who is awesome), ");
        }
        if (cbNothing.isChecked()){
            tv.append("nothing! Everything sucks, ");
        }

        tv.append("and that's it.");

    }

    public void submitFeedback(View v){
        Toast.makeText(v.getContext(), R.string.thank_you_feedback, Toast.LENGTH_LONG).show();
        finish();
    }
}