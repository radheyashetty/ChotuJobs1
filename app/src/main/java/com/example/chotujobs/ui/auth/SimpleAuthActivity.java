package com.example.chotujobs.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chotujobs.R;
import com.example.chotujobs.ui.laborer.LaborerDashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SimpleAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_modern);

        // For demo purposes, just navigate to dashboard when login is clicked
        MaterialButton loginButton = findViewById(R.id.loginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(v -> {
                // Navigate to laborer dashboard for demo
                Intent intent = new Intent(this, LaborerDashboardActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
