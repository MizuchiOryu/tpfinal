package com.example.inventorymanagement.data.service;

import com.example.inventorymanagement.data.model.Local;
import com.example.inventorymanagement.data.model.Product;

import java.util.List;

public class ProductService {


    public List<Product>getAll(){
        return Product.listAll(Product.class);
    }

    public boolean isExist(String name){
        List<Product> check_user = Product.find(Product.class, "name = ?", name);
        if(check_user.size() > 0){
            return true;
        }
        return false;
    }

    public boolean ifPossibleToExtend(int add){
        List<Product> products = Product.listAll(Product.class);
        Local limit = Local.listAll(Local.class).get(0);

        int final_count = 0;
        for (Product element : products)
        {
            // only changes num, not the array element
            final_count = final_count + element.getCurrent();
        }

        if (final_count + add < limit.getLimit_max()){
            return true;
        }
        return false;
    }

}
