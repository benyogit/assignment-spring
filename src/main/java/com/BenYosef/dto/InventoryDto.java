package com.BenYosef.dto;

import java.io.Serializable;

public class InventoryDto implements Serializable {

    private Long item_no;
    private int amount;
    private String name;
    private String inventory_code;

    public InventoryDto(Long item_no, int amount, String name, String inventory_code) {
        this.item_no = item_no;
        this.amount = amount;
        this.name = name;
        this.inventory_code = inventory_code;
    }


    public Long getItem_no() {
        return item_no;
    }

    public void setItem_no(Long item_no) {
        this.item_no = item_no;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventory_code() {
        return inventory_code;
    }

    public void setInventory_code(String inventoryCode) {
        this.inventory_code = inventoryCode;
    }
}
