package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;

public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
