package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.EmptyCartException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.InvalidCardException;
import com.example.Ecommerce.Model.Item;

public interface CartService {
    CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}
