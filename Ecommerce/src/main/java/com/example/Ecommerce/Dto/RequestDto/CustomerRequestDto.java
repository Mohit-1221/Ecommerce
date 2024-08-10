package com.example.Ecommerce.Dto.RequestDto;

import com.example.Ecommerce.Enums.Gender;
import com.example.Ecommerce.Model.Card;
import com.example.Ecommerce.Model.Cart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {
    String name;

    String emailId;

    String mobNo;

    Gender gender;

}
