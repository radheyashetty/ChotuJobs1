package com.example.chotujobs.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.chotujobs.R;
import com.example.chotujobs.databinding.FragmentRegisterBinding;
import com.example.chotujobs.data.model.UserType;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class RegisterFragment extends AuthFragment {

    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
    }

    private void setupUI() {
        setupUserTypeSpinner();
        binding.registerButton.setOnClickListener(v -> performRegister());
    }

    private void setupUserTypeSpinner() {
        String[] userTypes = {
            getString(R.string.laborer),
            getString(R.string.contractor),
            getString(R.string.agent)
        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            userTypes
        );
        
        binding.userTypeSpinner.setAdapter(adapter);
    }

    private void performRegister() {
        String name = binding.nameEditText.getText().toString().trim();
        String email = binding.emailEditText.getText().toString().trim();
        String phone = binding.phoneEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();
        String location = binding.locationEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty() || location.isEmpty()) {
            if (authListener != null) {
                authListener.onAuthError("Please fill in all fields");
            }
            return;
        }

        if (!password.equals(confirmPassword)) {
            if (authListener != null) {
                authListener.onAuthError("Passwords do not match");
            }
            return;
        }

        if (password.length() < 6) {
            if (authListener != null) {
                authListener.onAuthError("Password must be at least 6 characters");
            }
            return;
        }

        // Get selected user type
        UserType userType = UserType.LABORER;
        int selectedPosition = binding.userTypeSpinner.getSelectedItemPosition();
        switch (selectedPosition) {
            case 0:
                userType = UserType.LABORER;
                break;
            case 1:
                userType = UserType.CONTRACTOR;
                break;
            case 2:
                userType = UserType.AGENT;
                break;
        }

        // TODO: Implement actual registration logic with API call
        // For now, simulate successful registration
        if (authListener != null) {
            authListener.onLoadingChanged(true);
            
            UserType finalUserType = userType;
            // Simulate API call delay
            binding.getRoot().postDelayed(() -> {
                if (authListener != null) {
                    authListener.onLoadingChanged(false);
                    // Mock successful registration
                    authListener.onAuthSuccess("user123", "token123", finalUserType);
                }
            }, 2000);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

