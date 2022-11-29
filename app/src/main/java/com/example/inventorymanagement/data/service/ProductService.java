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

    public Product findByName(String name){
        List<Product> check_user = Product.find(Product.class, "name = ?", name);
        return check_user.get(0);
    }

    public Integer getSumCurrent(){
        List<Product> products = Product.listAll(Product.class);

        int final_count = 0;
        for (Product element : products)
        {
            final_count = final_count + element.getCurrent();
        }
        return final_count;
    }

    public Integer getLimitCurrent(){
        List<Product> products = Product.listAll(Product.class);

        int final_count = 0;
        for (Product element : products)
        {
            final_count = final_count + element.getLimit_max();
        }
        return final_count;
    }

    public boolean ifPossibleToExtend(int add){
        Local limit = Local.listAll(Local.class).get(0);
        int final_count = this.getLimitCurrent();
        if (final_count + add < limit.getLimit_max()){
            return true;
        }
        return false;
    }

    public boolean ifPossibleToModify(int oldValue,int newValue){
        Local limit = Local.listAll(Local.class).get(0);
        int final_count = this.getLimitCurrent();
        final_count = final_count - oldValue;
        if (final_count + newValue < limit.getLimit_max()){
            return true;
        }
        return false;
    }

    public Integer getLimitAvailable(){
        int final_count = this.getSumCurrent();
        Local limit = Local.listAll(Local.class).get(0);
        return limit.getLimit_max() - final_count;
    }

    public double getPourcentUse(){
        int final_count = this.getSumCurrent();
        Local limit = Local.listAll(Local.class).get(0);
        double percentage = 100.0 * final_count / limit.getLimit_max();
        return percentage;
    }



}
