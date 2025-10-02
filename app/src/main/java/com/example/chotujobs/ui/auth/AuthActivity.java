package com.example.chotujobs.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.chotujobs.R;
import com.example.chotujobs.databinding.ActivityAuthBinding;
import com.example.chotujobs.ui.laborer.LaborerDashboardActivity;
import com.example.chotujobs.ui.contractor.ContractorDashboardActivity;
import com.example.chotujobs.ui.agent.AgentDashboardActivity;
import com.example.chotujobs.data.model.UserType;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class AuthActivity extends AppCompatActivity implements AuthFragment.AuthListener {

    private ActivityAuthBinding binding;
    private AuthPagerAdapter pagerAdapter;
    
    private static final String PREFS_NAME = "ChotuJobsPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_TYPE = "userType";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_ACCESS_TOKEN = "accessToken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupViewPager();
    }
    
    private void setupViewPager() {
        pagerAdapter = new AuthPagerAdapter(this);
        binding.viewPager.setAdapter(pagerAdapter);
        
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Login");
                            break;
                        case 1:
                            tab.setText("Register");
                            break;
                    }
                }).attach();
    }

    @Override
    public void onAuthSuccess(String userId, String accessToken, UserType userType) {
        // Save login state
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_TYPE, userType.name());
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
        
        // Navigate to appropriate dashboard
        navigateToDashboard(userType);
    }

    @Override
    public void onAuthError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        binding.viewPager.setUserInputEnabled(!isLoading);
        binding.tabLayout.setEnabled(!isLoading);
    }
    
    private void navigateToDashboard(UserType userType) {
        Intent intent;
        switch (userType) {
            case LABORER:
                intent = new Intent(this, LaborerDashboardActivity.class);
                break;
            case CONTRACTOR:
                intent = new Intent(this, ContractorDashboardActivity.class);
                break;
            case AGENT:
                intent = new Intent(this, AgentDashboardActivity.class);
                break;
            default:
                intent = new Intent(this, LaborerDashboardActivity.class);
                break;
        }
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

