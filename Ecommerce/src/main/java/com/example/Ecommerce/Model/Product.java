package com.example.Ecommerce.Model;

import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "product_name")
    String name;

    @Column(name = "price")
    Integer price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    Category category;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
