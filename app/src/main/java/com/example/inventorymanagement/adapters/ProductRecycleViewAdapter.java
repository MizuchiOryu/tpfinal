package com.example.inventorymanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public ProductRecycleViewAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_card,parent,false);
        final ProductHolder holder = new ProductHolder(view);

        holder.itemView.setOnClickListener(l -> {
            Product product = products.get(holder.getAdapterPosition() + 1);

            AppCompatActivity activity = (AppCompatActivity) l.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new ProductDetailFragment(product)).commit();
        });
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.setDetails(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {
        TextView productName,productLimit,productCurrent;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameView);
            productLimit = itemView.findViewById(R.id.limitNumberView);
            productCurrent = itemView.findViewById(R.id.currentView);
        }

        public void setDetails(Product product){
            productName.setText(product.getName());
            productLimit.setText("0");
            productLimit.setText("0");
            //productCurrent.setText(product.getCurrent());
            //productCurrent.setText(product.getCurrent());
        }
    }
}
