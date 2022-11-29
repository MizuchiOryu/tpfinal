package com.example.inventorymanagement.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.inventorymanagement.R;
import com.example.inventorymanagement.data.model.Product;
import com.example.inventorymanagement.data.service.ProductService;
import com.example.inventorymanagement.databinding.FragmentAddProductBinding;
import com.example.inventorymanagement.databinding.FragmentDeleteProductBinding;
import com.example.inventorymanagement.ui.HomeFragment;

public class ProductDeleteFragment extends Fragment {
    FragmentDeleteProductBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDeleteProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeFragment fragment = new HomeFragment();
        ProductService service = new ProductService();
        binding.delete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String name = binding.name.getText().toString();
                        if(service.isExist(name)){
                            Product selected = service.findByName(name);
                            selected.delete();
                            Toast.makeText(getContext(), "Le produit a étais supprimer", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Aucun produit ne posséde ce nom", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        binding.productDetailBackButton.setOnClickListener(
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