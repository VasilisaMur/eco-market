package com.kr.ecomarket.repo;


import com.kr.ecomarket.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Long> {
    List<Item> findAllByCategoryId(int categoryId);

    List <Item> findAll();

    Item findById(int id);

    Long deleteById(int id);
}

