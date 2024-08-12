package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Exception.SellerNotFoundException;
import com.example.Ecommerce.Model.Seller;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    SellerResponseDto updateSeller(String sellerEmailId, String name, String mobNo) throws SellerNotFoundException;

    List<String> getSellerByCategory(Category category);
}
