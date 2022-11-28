package com.example.inventorymanagement.data.model;

import com.orm.SugarRecord;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Product extends SugarRecord {

    public UUID id;
    public String name;
    public Integer limit_max;
    public Integer current;

    public Product(){}

    public Product(String name, Integer limit_max,Integer current) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.limit_max = limit_max;
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit_max() {
        return limit_max;
    }

    public void setLimit_max(Integer limit_max) {
        this.limit_max = limit_max;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }
}