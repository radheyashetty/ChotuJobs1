package com.example.chotujobs.ui.laborer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.chotujobs.databinding.FragmentLaborerBidsBinding;
// import dagger.hilt.android.AndroidEntryPoint;

// @AndroidEntryPoint
public class LaborerBidsFragment extends Fragment {

    private FragmentLaborerBidsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLaborerBidsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
    }

    private void setupUI() {
        // TODO: Implement bid management functionality
        binding.textView.setText("Bids Fragment - Manage your job bids");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

