package com.BenYosef.controller;


import com.BenYosef.dto.ItemDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BenYosef.entity.Item;
import com.BenYosef.service.ApiService;
import com.BenYosef.exception.ResourceNotFoundException;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin()
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private ApiService apiService =new ApiService();


    @ApiOperation("Return a JSON object, one key with list of all items in the stock, and the other one with ok message ")
    @GetMapping("/")
    public List<Item> getAll(){
        Map<String , Object> res= new HashMap<>() ;
        List<Item> items =  this.apiService.getAllItems();
        return items;

    }


    @ApiOperation("Return An Item that was selected by id(item_no) in JSON")
    @GetMapping("/{item_no}")
    public Item getItemById(@PathVariable("item_no") Long no)  throws ResourceNotFoundException{

        return apiService.findItemById(no);
    }
    @ApiOperation("Get An Item {name:'name', detail: 'detail' , item_no: null}because it is not updating a new item)" +
            " and create and return a new Item")
    @PostMapping("/")
    public Item createItem(@Valid @RequestBody ItemDTO item)throws Exception{

        return apiService.createItem(item);
    }

    @ApiOperation("Return An JSON object with message if the action was success")
    @DeleteMapping("/{item_no}")
    public Map<String , Object> deleteItemById(@PathVariable( "item_no") Long no)
    throws ResourceNotFoundException {

        System.out.println("delete: item no is:"+ no);

        Map<String , Object> res= new HashMap<>();
        res.put("message", "ok");
        apiService.deleteItemById(no);
        return res;

    }


}
