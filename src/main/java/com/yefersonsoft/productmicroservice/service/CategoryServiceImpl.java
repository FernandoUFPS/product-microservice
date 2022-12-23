package com.yefersonsoft.productmicroservice.service;

import com.yefersonsoft.productmicroservice.entity.Category;
import com.yefersonsoft.productmicroservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());
        if (null == categoryDB){
            return null;
        }
        categoryDB.setName(category.getName());
        return categoryRepository.save(categoryDB);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category categoryDB = getCategory(id);
        if (null == categoryDB){
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
