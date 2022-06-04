package com.kr.ecomarket.service;

import com.kr.ecomarket.models.Item;
import com.kr.ecomarket.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private ItemRepo ItemRepo;

    @Autowired
    public ItemService(ItemRepo ItemRepo){
        this.ItemRepo = ItemRepo;
    }

    public List<Item> getAllItemsById(){
        return ItemRepo.findAll();
    }

    public List<Item> getAllItemsByCategoryId(int categoryId) {
        return ItemRepo.findAllByCategoryId(categoryId);
    }

    public Item getItemById(int id){
        return ItemRepo.findById(id);
    }

    public void saveItem(Item items){
        ItemRepo.save(items);
    }

    public void deleteItemById(int id){
        ItemRepo.deleteById(id);
    }
}