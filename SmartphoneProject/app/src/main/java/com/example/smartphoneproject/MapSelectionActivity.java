package com.example.smartphoneproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.example.smartphoneproject.databinding.ActivityMapSelectionBinding;

import java.util.ArrayList;

public class MapSelectionActivity extends AppCompatActivity {
    private ActivityMapSelectionBinding binding;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Map Selection");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progress_loading);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void handleMap(View v){
        //show progress bar immediately
        progressBar.setVisibility(View.VISIBLE);

        int id = v.getId();
        Bundle locations = new Bundle();

        if (id == R.id.bCountries){
            Log.d("Country", "Countries!");
            locations.putParcelable("Spain", new MarkerData("Spain", 40.46, -3.75));
            locations.putParcelable("Italy", new MarkerData("Italy", 41.87, 12.57));
            locations.putParcelable("France", new MarkerData("France", 46.23, 2.21));
            locations.putParcelable("Belgium", new MarkerData("Belgium", 50.50, 4.47));
            locations.putParcelable("Germany", new MarkerData("Germany", 51.16, 10.45));
            locations.putParcelable("Switzerland", new MarkerData("Switzerland", 46.82, 8.23));
            locations.putParcelable("Croatia", new MarkerData("Croatia", 45.20, 15.20));
            locations.putParcelable("Portugal", new MarkerData("Portugal", 39.40, -8.22));
            locations.putParcelable("UK", new MarkerData("UK", 52.24,-0.92));
            locations.putParcelable("Ireland", new MarkerData("Ireland", 53.78, -7.31));
            locations.putParcelable("Tunisia", new MarkerData("Tunisia", 33.89, 9.54));
            locations.putParcelable("Cyprus", new MarkerData("Cyprus", 35.13, 33.43));
            locations.putParcelable("Denmark", new MarkerData("Denmark", 56.26, 9.50));
            locations.putParcelable("Estonia", new MarkerData("Estonia", 58.59, 25.01));
            locations.putParcelable("Sweden", new MarkerData("Sweden", 59.61,17.70));

            locations.putString("Option", "countries");
        }
        else if (id == R.id.bBars){
            Log.d("Bar", "Bars!");
            locations.putParcelable("Oxford Pub", new MarkerData("Oxford Pub", 49.0083864, 8.4123632));
            locations.putParcelable("De Blauwe Kater", new MarkerData("De Blauwe Kater", 50.8814191, 4.6991438));
            locations.putParcelable("Thomas Stapleton Irish Pub", new MarkerData("Thomas Stapleton Irish Pub", 50.8775144, 4.7006266));
            locations.putParcelable("Downtown Jack", new MarkerData("Downtown Jack", 50.8732071, 4.7015429));
            locations.putParcelable("Omnipollos Flora", new MarkerData("Omnipollos Flora", 59.3392884, 18.0738475));

            locations.putString("Option", "bars");

        }

        Intent in = new Intent(this, MapsActivity.class);
        in.putExtras(locations);
        startActivity(in);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Hide the loading bar whenever the activity becomes visible again
        ProgressBar progressBar = findViewById(R.id.progress_loading);
        progressBar.setVisibility(View.GONE);
    }


}