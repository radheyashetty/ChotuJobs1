package com.example.chotujobs.ui.contractor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chotujobs.R;
import com.example.chotujobs.ui.auth.AuthActivity;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class ContractorDashboardActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ChotuJobsPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_dashboard);
        
        // TODO: Implement contractor dashboard functionality
    }
    
    public void logout() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        
        Intent intent = new Intent(this, AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

