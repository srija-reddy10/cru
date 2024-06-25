package com.spring.products.service;

import com.spring.products.Entity.Products;
import com.spring.products.Repository.ProductsRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepo prepo;

    //method to add single product
    public Products addproduct(Products products){
        return prepo.save(products);
    }

    //method to add multiple products at a time
    public List<Products> addproducts(List<Products> products){
        return prepo.saveAll(products);
    }

    //method to get all the products
    public List<Products> getallproducts(){
        return prepo.findAll();
    }

    //method to get product by id
    public Products getbyId(Integer id){
        return prepo.findById(id).orElse(null) ;
    }

    //method to get product by name
    public Products getbyName(String name){
        return prepo.findByName(name);
    }

    //method to delete the products
    public String delete(int id){
        Products existingpro = prepo.findById(id).orElse(null);
        if (existingpro != null) {
            prepo.deleteById(id);
            return "product deleted: " + id;
        }
        return "product not found: " + id;
    }

    //method to update the products
    public Products update(Products products){
        if(products == null){
            throw new HttpMessageNotReadableException("Request body is missing or malformed");
        }
        if(products.getId() == null){
            throw new IllegalArgumentException("Id is NUll");
        }
        Products existingProduct = prepo.findById(products.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(products.getName());
            existingProduct.setQuantity(products.getQuantity());
            existingProduct.setPrice(products.getPrice());
            return prepo.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + products.getId());
        }

    }
}
