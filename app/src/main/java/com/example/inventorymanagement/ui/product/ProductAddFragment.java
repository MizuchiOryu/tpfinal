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
import com.example.inventorymanagement.data.model.Limit;
import com.example.inventorymanagement.data.model.Product;
import com.example.inventorymanagement.data.service.ProductService;
import com.example.inventorymanagement.databinding.FragmentAddProductBinding;
import com.example.inventorymanagement.databinding.FragmentStartBinding;
import com.example.inventorymanagement.ui.HomeFragment;

public class ProductAddFragment extends Fragment {
    FragmentAddProductBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeFragment fragment = new HomeFragment();
        ProductService service = new ProductService();
        binding.confirm.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String name = binding.name.toString();
                        if(!service.isExist(name)){
                            Toast.makeText(getContext(), "Produit deja existant", Toast.LENGTH_SHORT).show();
                        }
                        Integer limit = new Integer(binding.limitNumber.toString()).intValue();
                        if(service.ifPossibleToExtend(limit)){
                            Toast.makeText(getContext(), "Impossible d'ajouter cette quantité de produit cela depasse la limite", Toast.LENGTH_SHORT).show();
                        }
                        Integer stock = new Integer(binding.limitNumber.toString()).intValue();
                        if(stock > limit){
                            Toast.makeText(getContext(), "Impossible d'ajouter plus que la limite du stock", Toast.LENGTH_SHORT).show();
                        }
                        Product product = new Product(name,limit,stock);
                        product.save();
                        Toast.makeText(getContext(), "Le produit a été ajouter vous allez être redirigés", Toast.LENGTH_SHORT).show();
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();

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