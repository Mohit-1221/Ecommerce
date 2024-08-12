package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String sellerEmailId);
}
