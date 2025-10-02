package com.example.chotujobs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log; // For logging errors
import androidx.annotation.NonNull; // For clarifying non-null expectations
import androidx.appcompat.app.AppCompatActivity;
import com.example.chotujobs.ui.auth.AuthActivity;
import com.example.chotujobs.ui.laborer.LaborerDashboardActivity;
import com.example.chotujobs.ui.contractor.ContractorDashboardActivity;
import com.example.chotujobs.ui.agent.AgentDashboardActivity;
import com.example.chotujobs.data.model.UserType;
// import dagger.hilt.android.AndroidEntryPoint; // Uncomment if using Hilt
// import javax.inject.Inject; // Uncomment if using Hilt and injecting SharedPreferences

// @AndroidEntryPoint // Uncomment if using Hilt
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // For logging
    private static final String PREFS_NAME = "ChotuJobsPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_TYPE = "userType";

    // @Inject // Uncomment if using Hilt and injecting SharedPreferences
    // SharedPreferences prefs; // Example if Hilt is used to provide SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No setContentView needed if this Activity is just for routing and finishes immediately.

        // If not using Hilt injection for SharedPreferences:
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        boolean isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, false);

        if (isLoggedIn) {
            String userTypeString = prefs.getString(KEY_USER_TYPE, null); // Get string or null
            UserType userType = UserType.LABORER; // Default to LABORER if something goes wrong

            if (userTypeString != null) {
                try {
                    userType = UserType.valueOf(userTypeString);
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, "Invalid UserType string in SharedPreferences: " + userTypeString, e);
                    // Fallback to a default user type (already set) or navigate to Auth
                    // For example, if critical, you might want to force re-authentication:
                    // navigateToAuth();
                    // finish();
                    // return;
                }
            } else {
                // This case should ideally not happen if isLoggedIn is true
                // and userType was saved correctly during login.
                Log.w(TAG, "User is logged in but UserType is null in SharedPreferences. Defaulting to LABORER.");
            }
            navigateToDashboard(userType);
        } else {
            navigateToAuth();
        }
        finish(); // Finish this activity to remove it from the back stack
    }

    private void navigateToAuth() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void navigateToDashboard(@NonNull UserType userType) {
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
                // This case should ideally be covered by the enum and robust parsing,
                // but as a final fallback, navigate to a sensible default or auth.
                Log.w(TAG, "Unexpected UserType in navigateToDashboard: " + userType + ". Defaulting to Laborer dashboard.");
                intent = new Intent(this, LaborerDashboardActivity.class);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
