package com.example.smartphoneproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartphoneproject.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("About this App");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //functionality for social buttons
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvRepo = findViewById(R.id.tvRepo);
        TextView tvGithub = findViewById(R.id.tvGithub);
        TextView tvLinkedin = findViewById(R.id.tvLinkedin);

        //open default email app and compose email
        tvEmail.setOnClickListener(v -> {
            String email = getString(R.string.support_email);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + email)); // only email apps respond
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        //navigate to Github repo for this app (note: repository is private for now)
        tvRepo.setOnClickListener(v -> {
            String repoUrl = getString(R.string.github_repo);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(repoUrl));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        tvGithub.setOnClickListener(v -> {
            String profile = getString(R.string.github_profile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(profile));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        tvLinkedin.setOnClickListener(v -> {
            String linkedin = getString(R.string.linkedin);
            Intent intent;

            try {
                // Try to open in LinkedIn app
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/" + linkedin));
                intent.setPackage("com.linkedin.android"); // force LinkedIn app
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // fallback to browser
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/" + linkedin));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}