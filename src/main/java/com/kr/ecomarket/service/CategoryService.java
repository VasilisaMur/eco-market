package com.kr.ecomarket.service;

import com.kr.ecomarket.models.Category;
import com.kr.ecomarket.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService( CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public Category getCategoryById(int id){
        return categoryRepo.findById(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }
}
