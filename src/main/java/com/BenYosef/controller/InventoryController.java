package com.BenYosef.controller;



import com.BenYosef.dto.InventoryDto;
import com.BenYosef.entity.InventoryItem;
import com.BenYosef.exception.ResourceNotFoundException;
import com.BenYosef.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/")
    public Map<String,Object> getInventory(){
        Map<String,Object> res= new HashMap<>();
        List<InventoryItem> inventoryList= this.apiService.getAllInventoryItem();
        List<InventoryDto> inventoryDto=  new ArrayList<>();
        for(InventoryItem in : inventoryList){
            InventoryDto temp= new InventoryDto(in.getItem().getItem_no(),
                    in.getAmount(),in.getItem().getName(),in.getInventory_code().toString() );
            inventoryDto.add(temp);
        }
        res.put("inventoryItems", inventoryDto);
        res.put("count", this.apiService.getAllInventoryItem().size());
        return res;
    }

    @ApiOperation("Update an Inventory Item by the getting an inventory_code and an amount add/subtract")
    @PutMapping("/update/{inventory_code}")
    public Map<String,Object> increaseAmount(@PathVariable(value = "inventory_code")  UUID inventory_code,
                                             @Valid @RequestBody() InventoryItem item)
            throws ResourceNotFoundException {
        Map<String,Object> res= new HashMap<>();
        InventoryItem it= this.apiService.increaseAmount(inventory_code, item.getAmount());
        res.put("data", it);
        return res;
    }
    @ApiOperation("Delete an Inventory Item by the inventory_code")
    @DeleteMapping ("/delete/{inventory_code}")
    public Map<String,Object> deleteInventoryItem(@PathVariable(value = "inventory_code")  UUID inventory_code)
            throws ResourceNotFoundException {
        Map<String,Object> res= new HashMap<>();

        this.apiService.deleteInventoryItem(inventory_code);
        res.put("delete", "Success");

        return res;
    }

    @ApiOperation("Create an Inventory Item or updates an existing one by getting a Stock Item id(item_no)")
    @PostMapping("/")
    public Map<String,Object> createInventoryItem(@Valid @RequestBody() Map<String,Object> map){
        Map<String,Object> res= new HashMap<>();
        Long item_no= Long.parseLong(map.get("item_no").toString());
        InventoryItem it= this.apiService.createInventory(item_no);
        res.put("data", it);
        return res;

    }

}
