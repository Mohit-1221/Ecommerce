package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Enums.Gender;
import com.example.Ecommerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmailId(String customerEmailId);

   // @Query(value = "SELECT * FROM customer WHERE gender = 'FEMALE' AND age BETWEEN 20 AND 30",nativeQuery = true)
    @Query(value = "SELECT c FROM Customer c WHERE c.gender = :gender AND c.age BETWEEN :startAge AND :endAge",nativeQuery = false)
    List<Customer> findByGenderAndAgeBetween(Gender gender, int startAge, int endAge);

    @Query(value = "SELECT c FROM Customer c WHERE c.gender = :gender AND c.age < :age",nativeQuery = false)
    List<Customer> findByGenderAndAgeLessThan(Gender gender,int age);
}
