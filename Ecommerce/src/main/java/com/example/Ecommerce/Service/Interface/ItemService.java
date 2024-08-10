package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.OutOfStockException;
import com.example.Ecommerce.Exception.ProductNotFoundException;
import com.example.Ecommerce.Model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, OutOfStockException, InsufficientQuantityException;
}
