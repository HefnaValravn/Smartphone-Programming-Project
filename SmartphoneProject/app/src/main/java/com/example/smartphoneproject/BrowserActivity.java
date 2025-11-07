package com.example.smartphoneproject;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BrowserActivity extends AppCompatActivity {

    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browser);

        //important!
        browser = findViewById(R.id.wvWebsite);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            finish();
        });
    }


    public void browseUrl(View v){
        EditText et = findViewById(R.id.etUrl);
        //redundant but might as well keep it
        WebView browser = findViewById(R.id.wvWebsite);

        String url = et.getText().toString();
        if (!url.startsWith("https://")){
            url = "https://" + url;
        }

        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
    }
}