package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Model.Item;
import com.example.Ecommerce.Model.Product;

import java.util.List;

public class ItemTransformer {
    public static Item itemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto itemToResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
