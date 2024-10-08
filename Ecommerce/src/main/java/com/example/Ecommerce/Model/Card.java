package com.example.Ecommerce.Model;

import com.example.Ecommerce.Enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "card_no",unique = true,nullable = false)
    String cardNo;

    @Column(name = "cvv")
    Integer cvv;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @Column(name = "valid_till")
    Date validTill;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
