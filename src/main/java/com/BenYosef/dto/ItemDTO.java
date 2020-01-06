package com.BenYosef.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {


    private String name, details;

    public ItemDTO(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
