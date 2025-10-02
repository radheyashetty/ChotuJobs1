package com.example.chotujobs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chotujobs.R;
import com.example.chotujobs.ui.auth.SimpleAuthActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Navigate to auth after delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, SimpleAuthActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
