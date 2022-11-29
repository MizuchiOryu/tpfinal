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
import com.example.inventorymanagement.databinding.FragmentDetailProductBinding;
import com.example.inventorymanagement.ui.HomeFragment;
import com.example.inventorymanagement.ui.StockFragment;

import java.text.DateFormat;
import java.util.Date;

public class ProductDetailFragment extends Fragment {
    FragmentDetailProductBinding binding;
    Product product;

    public ProductDetailFragment(){

    }

    public ProductDetailFragment(Product order){
        this.product = order;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Product product_selected = this.product;
        ProductService service = new ProductService();
        StockFragment fragment = new StockFragment();

        binding.productDetailBackButton.setOnClickListener(l -> {
            AppCompatActivity activity = (AppCompatActivity) l.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new StockFragment()).commit();
        });

        binding.title.setText(product.getName());
        binding.limitNumber.setText(product.getLimit_max().toString());
        binding.current.setText(product.getCurrent().toString());

        binding.modify.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Integer limit = new Integer(binding.limitNumber.getText().toString()).intValue();
                        Integer stock = new Integer(binding.current.getText().toString()).intValue();
                        if(!service.ifPossibleToModify(product.getLimit_max(),limit)){
                            Toast.makeText(getContext(), "Impossible d'ajouter cette quantité de produit cela depasse la limite", Toast.LENGTH_SHORT).show();
                        }else if(stock > limit){
                            Toast.makeText(getContext(), "Impossible d'ajouter plus que la limite du stock", Toast.LENGTH_SHORT).show();
                        }else{
                            product_selected.setCurrent(stock);
                            product_selected.setLimit_max(limit);
                            product_selected.save();
                            Toast.makeText(getContext(), "Le produit a bien été modifié", Toast.LENGTH_SHORT).show();
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();
                        }
                    }
                }
        );

    }
}
