package com.example.inventorymanagement.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanagement.R;
import com.example.inventorymanagement.adapters.ProductRecycleViewAdapter;
import com.example.inventorymanagement.data.model.Local;
import com.example.inventorymanagement.data.model.Product;
import com.example.inventorymanagement.data.service.ProductService;
import com.example.inventorymanagement.databinding.FragmentHomeBinding;
import com.example.inventorymanagement.databinding.FragmentStockBinding;

import java.util.ArrayList;

public class StockFragment extends Fragment {
    ArrayList<Product> productList;
    FragmentStockBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            productList = new ArrayList<>(Product.listAll(Product.class));
        }catch (Exception exception){
            productList = new ArrayList<>();
        }
        binding = FragmentStockBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeFragment fragment = new HomeFragment();
        binding.productDetailBackButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
                    }
                }
        );
        ProductService service = new ProductService();
        Local limit = Local.listAll(Local.class).get(0);

        Integer pourcent_use = (int)Math.round(service.getPourcentUse());
        Integer int_available = service.getLimitAvailable();
        String transform_pourcent_string = pourcent_use.toString() + "%";
        String transform_int_available = int_available.toString() + " / " + limit.getLimit_max().toString();
        binding.pourcentCurrent.setText(transform_pourcent_string);
        binding.available.setText(transform_int_available);


        ProductRecycleViewAdapter productRecycleViewAdapter = new ProductRecycleViewAdapter(getContext(), productList);

        RecyclerView recyclerView = binding.productRecycleView;
        recyclerView.setAdapter(productRecycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

    }

}