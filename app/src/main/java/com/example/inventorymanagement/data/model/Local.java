package com.example.inventorymanagement.data.model;

import com.orm.SugarRecord;

import java.util.UUID;

public class Local extends SugarRecord {

    public UUID id;
    public Integer limit_max;

    public Local(){}

    public Local(Integer limit_max){

        this.id = UUID.randomUUID();
        this.limit_max = limit_max;
    }

    public Integer getLimit_max() {
        return limit_max;
    }

    public void setLimit_max(Integer limit_max) {
        this.limit_max = limit_max;
    }
}