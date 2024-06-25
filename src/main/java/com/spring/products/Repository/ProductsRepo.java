package com.spring.products.Repository;

import com.spring.products.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,Integer> {

    Products findByName(String name);
}
