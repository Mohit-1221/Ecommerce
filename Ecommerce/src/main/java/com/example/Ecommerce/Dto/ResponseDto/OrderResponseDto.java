package com.example.Ecommerce.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String customerName;

    String orderNo;  // UUID

    int totalValue;

    String cardUsed;

    Date orderDate;

    List<ItemResponseDto> items;
}
