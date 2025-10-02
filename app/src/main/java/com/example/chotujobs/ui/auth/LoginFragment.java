package com.example.chotujobs.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.chotujobs.databinding.FragmentLoginBinding;
import com.example.chotujobs.data.model.UserType;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class LoginFragment extends AuthFragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
    }

    private void setupUI() {
        binding.loginButton.setOnClickListener(v -> performLogin());
    }

    private void performLogin() {
        String email = binding.emailEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            if (authListener != null) {
                authListener.onAuthError("Please fill in all fields");
            }
            return;
        }

        // TODO: Implement actual login logic with API call
        // For now, simulate successful login
        if (authListener != null) {
            authListener.onLoadingChanged(true);
            
            // Simulate API call delay
            binding.getRoot().postDelayed(() -> {
                if (authListener != null) {
                    authListener.onLoadingChanged(false);
                    // Mock successful login
                    authListener.onAuthSuccess("user123", "token123", UserType.LABORER);
                }
            }, 1500);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

