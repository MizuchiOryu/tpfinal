package com.example.inventorymanagement.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanagement.R;
import com.example.inventorymanagement.data.model.Product;
import com.example.inventorymanagement.ui.product.ProductDetailFragment;

import java.util.ArrayList;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.ProductHolder> {
    Context context;
    ArrayList<Product> products;
    Integer posSelected;

    public ProductRecycleViewAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_card,parent,false);
        final ProductHolder holder = new ProductHolder(view);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.setDetails(product);

        holder.itemView.setOnClickListener(l -> {
            Product product_redirect = products.get(holder.getLayoutPosition());
            AppCompatActivity activity = (AppCompatActivity) l.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new ProductDetailFragment(product_redirect)).commit();
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {
        TextView productName,productLimit,productCurrent;
        ProgressBar progress_bar;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameView);
            productLimit = itemView.findViewById(R.id.limitNumberView);
            productCurrent = itemView.findViewById(R.id.currentView);
            progress_bar = itemView.findViewById(R.id.progress_bar);
        }

        public void setDetails(Product product){
            productName.setText(product.getName());
            String current = product.getCurrent().toString();
            String max = "/ " + product.getLimit_max().toString();
            productCurrent.setText(current);
            productLimit.setText(max);
            double percentage = 100.0 * product.getCurrent() / product.getLimit_max();
            int progress = (int)Math.round(percentage);
            progress_bar.setProgress(progress);
        }



    }
}
