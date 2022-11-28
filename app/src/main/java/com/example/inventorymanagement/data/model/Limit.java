package com.example.inventorymanagement.data.model;

import com.orm.SugarRecord;

import java.util.UUID;

public class Limit extends SugarRecord {

    public UUID id;
    public Integer limit;

    public Limit(){}

    public Limit(Integer limit){

        this.id = UUID.randomUUID();
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}