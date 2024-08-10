package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Model.Cart;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static Cart initialCart(Customer customer){ // for customer login
        return Cart.builder()
                .cartTotal(0)  // initially total cart is 0
                .customer(customer)
                .build();
    }

    public static CartResponseDto CartToResponseDto(Cart cart){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.itemToResponseDto(item));
        }
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
