package com.example.Ecommerce.Dto.RequestDto;

import com.example.Ecommerce.Enums.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String customerEmailId;

    String cardNo;

    Integer cvv;

    CardType cardType;

    Date validTill;
}
