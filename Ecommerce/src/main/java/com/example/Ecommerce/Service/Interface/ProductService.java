package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);
}
