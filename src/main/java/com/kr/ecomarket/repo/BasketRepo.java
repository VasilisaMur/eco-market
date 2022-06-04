package com.kr.ecomarket.repo;

import com.kr.ecomarket.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BasketRepo extends JpaRepository<Basket, Integer> {

    Basket findByUserIdAndItemId(int userId, int itemId);

    List<Basket> findAllByUserId(int userId);

    Basket findById(int id);

    Long deleteById(int id);

    @Transactional
    Long deleteAllByUserId(int userId);
}
