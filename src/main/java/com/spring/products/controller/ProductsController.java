package com.spring.products.controller;


import com.spring.products.Entity.Products;
import com.spring.products.service.ProductsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService pservice;

    //postmapping
    @PostMapping("/addproduct")
    public Products saveproduct(@RequestBody Products products){
        return pservice.addproduct(products);
    }

    @PostMapping("/addproducts")
    public List<Products> saveproducts(@RequestBody List<Products> products){
        return pservice.addproducts(products);
    }

    //getmapping
    @GetMapping("/getproducts")
    public List<Products> findallproducts(){
        return pservice.getallproducts();
    }

    @GetMapping("/getbyId/{id}")
    public Products findprobyid(@PathVariable Integer id){
        return pservice.getbyId(id);
    }

    @GetMapping("/getbyName/{name}")
    public Products findprobyName(@PathVariable String name){
        return pservice.getbyName(name);
    }
    //putmapping

    @PutMapping("/update")
    public Products updatepro(@RequestBody Products products){
        return pservice.update(products);
    }

    //deletemapping
    @DeleteMapping("/delete/{id}")
    public String deletepro(@PathVariable int id){
        return pservice.delete(id);
    }






    //exception handler
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
