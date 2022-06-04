package com.kr.ecomarket.service;

import com.kr.ecomarket.models.Basket;;
import com.kr.ecomarket.repo.BasketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private BasketRepo basketRepo;

    @Autowired
    public BasketService(BasketRepo basketRepo) {
        this.basketRepo = basketRepo;
    }

    public List<Basket> getPurchasesByUserId(int userId) {
        return basketRepo.findAllByUserId(userId);
    }

    public Basket getPurchaseByUserIdAndItemId(int userId, int itemId){
        return basketRepo.findByUserIdAndItemId(userId, itemId);
    }

    public Basket getPurchaseById (int id) {
        return basketRepo.findById(id);
    }

    public void savePurchase(Basket purchase) {
        basketRepo.save(purchase);
    }

    public void deletePurchaseById(int id) {
        basketRepo.deleteById(id);
    }

    public void deletePurchase(Basket purchase){
        basketRepo.delete(purchase);
    }

    public void deleteAllByUserId(int userId)
    {
        basketRepo.deleteAllByUserId(userId);
    }
}
