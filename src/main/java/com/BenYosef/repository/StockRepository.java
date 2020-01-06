package com.BenYosef.repository;


import com.BenYosef.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Item,Long> {



}
