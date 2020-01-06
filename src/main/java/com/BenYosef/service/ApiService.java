package com.BenYosef.service;


import com.BenYosef.dto.InventoryDto;
import com.BenYosef.dto.ItemDTO;
import com.BenYosef.entity.InventoryItem;
import com.BenYosef.entity.Item;
import com.BenYosef.exception.ResourceNotFoundException;
import com.BenYosef.repository.InventoryRepository;
import com.BenYosef.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class ApiService {


    @Autowired
    private StockRepository stockRepos;
    @Autowired
    private InventoryRepository inventoryRepos;

    public List <Item > getAllItems() {
        return stockRepos.findAll();
    }

    public List <InventoryItem > getAllInventoryItem() {
        return inventoryRepos.findAll();
    }


    public void deleteInventoryItem(UUID inventory_code) throws ResourceNotFoundException {

        InventoryItem it= this.inventoryRepos.findById(inventory_code)
                .orElseThrow(()->
                        new ResourceNotFoundException("Can't delete the Inventory item. inventory_code does not exist"));
        this.inventoryRepos.deleteById(inventory_code);
    }
    public InventoryItem increaseAmount(UUID inventory_code, int amount)
            throws ResourceNotFoundException{
        InventoryItem inventoryItem =  this.inventoryRepos.findById(inventory_code).orElseThrow(()->
                new ResourceNotFoundException("Can't update the Inventory item's amount. inventory_code does not exist"));

        if(inventoryItem.getAmount()+amount <1){
            throw new ResourceNotFoundException("Can't update the Inventory item's amount.amount must be above 0");
        }
        inventoryItem.setAmount(inventoryItem.getAmount()+amount);
        this.inventoryRepos.save(inventoryItem);
        return inventoryItem;



    }
    public Item findItemById(Long id)
            throws ResourceNotFoundException{
        Item it= this.stockRepos.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Can't Find the Item. item_no does not exist"));
        return stockRepos.getOne(id);
    }
    public void deleteItemById(Long id)
            throws ResourceNotFoundException{

        Item it= this.stockRepos.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Can't Delete the Item. item_no does not exist"));
         this.stockRepos.deleteById(id);
    }
    public Item createItem(ItemDTO itemDTO) throws Exception {

        if (itemDTO.getName().length()<3 ||itemDTO.getDetails().length()<3){
            throw new Exception("Bad Crediantials. Item name and details must contain at least 3 characters");
        }
        Item item= new Item(itemDTO.getName(),itemDTO.getDetails());
        return stockRepos.save(item);
    }

    public InventoryItem createInventory(Long item_no) {


        List<InventoryItem >inventoryItems = this.inventoryRepos.findAll();
        int i;
        for ( i=0; i<inventoryItems.size();i++){
            if(inventoryItems.get(i).getItem().getItem_no()==item_no){

                inventoryItems.get(i).setAmount(inventoryItems.get(i).getAmount()+1);
                this.inventoryRepos.save(inventoryItems.get(i));
                return inventoryItems.get(i);

            }
        }
        InventoryItem inv= new InventoryItem(1, UUID.randomUUID());
        inv.setItem(stockRepos.getOne(item_no));
        this.inventoryRepos.save(inv);
        return inv;

    }





}
