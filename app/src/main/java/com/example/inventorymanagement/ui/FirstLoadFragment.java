package com.example.inventorymanagement.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.inventorymanagement.R;
import com.example.inventorymanagement.data.model.Local;
import com.example.inventorymanagement.databinding.FragmentStartBinding;

import java.util.List;

public class FirstLoadFragment extends Fragment {
    FragmentStartBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeFragment fragment = new HomeFragment();
        binding.confirm.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Local.deleteAll(Local.class);
                        Integer number = new Integer(binding.limitNumber.getText().toString()).intValue();
                        Local new_limit = new Local(number);
                        new_limit.save();
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();

                    }
                }
        );
        binding.skip.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
                    }
                }
        );
    }



}