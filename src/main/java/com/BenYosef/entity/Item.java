package com.BenYosef.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Table(name = "Items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long item_no;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="details", nullable = false)
    private String details;


    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    private InventoryItem inventoryItem;


    public Item(String name, String details) {
        this.name = name;
        this.details = details;
    }
    public Item() {

    }

    public long getItem_no() {
        return item_no;
    }

    public void setItem_no(long item_no) {
        this.item_no = item_no;
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