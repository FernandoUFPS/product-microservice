package com.yefersonsoft.productmicroservice.service;

import com.yefersonsoft.productmicroservice.entity.Category;
import com.yefersonsoft.productmicroservice.entity.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> listAllCategory();
    public Category getCategory(Long id);
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public boolean deleteCategory(Long id);
}
