package com.example.chotujobs.ui.laborer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.chotujobs.R;
import com.example.chotujobs.databinding.ActivityLaborerDashboardBinding;
import com.example.chotujobs.ui.auth.AuthActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class LaborerDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityLaborerDashboardBinding binding;
    private static final String PREFS_NAME = "ChotuJobsPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaborerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupBottomNavigation();
        
        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new LaborerJobsFragment());
        }
    }
    
    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        
        int itemId = item.getItemId();
        if (itemId == R.id.nav_jobs) {
            fragment = new LaborerJobsFragment();
        } else if (itemId == R.id.nav_bids) {
            fragment = new LaborerBidsFragment();
        } else if (itemId == R.id.nav_profile) {
            fragment = new LaborerProfileFragment();
        } else if (itemId == R.id.nav_reviews) {
            fragment = new LaborerReviewsFragment();
        }
        
        return fragment != null && loadFragment(fragment);
    }
    
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

