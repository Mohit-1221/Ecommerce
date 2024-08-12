package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
     List<Product> findByCategoryAndPrice(Category category, int price);

    List<Product> findByCategory(Category category);
}
