package com.example.inventorymanagement;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.inventorymanagement.databinding.ActivityMainBinding;
import com.example.inventorymanagement.ui.FirstLoadFragment;
import com.example.inventorymanagement.ui.StockFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = mainBinding.getRoot();
        this.setContentView(v);

        FirstLoadFragment firstFragmentStart = new FirstLoadFragment();
        replaceFragment(firstFragmentStart);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}