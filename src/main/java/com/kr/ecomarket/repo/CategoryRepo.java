package com.kr.ecomarket.repo;

import com.kr.ecomarket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findById(int id);
    Long deleteById(int id);
}

