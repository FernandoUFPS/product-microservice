package com.yefersonsoft.productmicroservice.repository;

import com.yefersonsoft.productmicroservice.entity.Category;
import com.yefersonsoft.productmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    public List<Product> findByCategory(Category category);
}