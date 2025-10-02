package com.example.chotujobs.ui.auth;

import com.example.chotujobs.data.model.UserType;

public abstract class AuthFragment extends androidx.fragment.app.Fragment {
    
    public interface AuthListener {
        void onAuthSuccess(String userId, String accessToken, UserType userType);
        void onAuthError(String error);
        void onLoadingChanged(boolean isLoading);
    }
    
    protected AuthListener authListener;
    
    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);
        if (context instanceof AuthListener) {
            authListener = (AuthListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AuthListener");
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        authListener = null;
    }
}

