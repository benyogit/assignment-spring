package com.BenYosef.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="Inventory")
public class InventoryItem implements Serializable {



    private int amount=1;

    @Id
    @Column( name= "inventory_code" )
    private UUID  inventory_code= UUID.randomUUID();



    @OneToOne()
    @JoinColumn(name = "item_no", referencedColumnName = "item_no")
    private Item item;

    public InventoryItem(){

    }
    public InventoryItem(int amount, UUID code){
        this.amount=amount;
        this.inventory_code=code;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UUID getInventory_code() {
        return inventory_code;
    }

    public void setInventory_code(UUID inventory_code) {
        this.inventory_code = inventory_code;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
