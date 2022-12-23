package com.yefersonsoft.productmicroservice.repository;

import com.yefersonsoft.productmicroservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
