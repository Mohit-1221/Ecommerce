package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.InvalidCardException;
import com.example.Ecommerce.Exception.ProductNotFoundException;
import com.example.Ecommerce.Model.Card;
import com.example.Ecommerce.Model.Cart;
import com.example.Ecommerce.Model.OrderEntity;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}
