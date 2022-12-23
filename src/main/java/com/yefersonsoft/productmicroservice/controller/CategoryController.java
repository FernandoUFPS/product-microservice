package com.yefersonsoft.productmicroservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yefersonsoft.productmicroservice.entity.Category;
import com.yefersonsoft.productmicroservice.entity.Product;
import com.yefersonsoft.productmicroservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    @GetMapping
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> categories = new ArrayList<>();
        categories = categoryService.listAllCategory();
        if (categories.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        Category category =  categoryService.getCategory(id);
        if (null==category){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Category categoryCreate =  categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreate);
    }

   @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        category.setId(id);
        Category categoryDB =  categoryService.updateCategory(category);
        if (categoryDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id){
        boolean deleteCategory = categoryService.deleteCategory(id);
        return (deleteCategory == false)? ResponseEntity.notFound().build() : ResponseEntity.ok(deleteCategory);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
