package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Model.Seller;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.Interface.SellerService;
import com.example.Ecommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // request dto to entity
        Seller seller = SellerTransformer.requestDtoToSellerEntity(sellerRequestDto);

        // save to db
        Seller savedSeller = sellerRepository.save(seller);

        // entity to response dto
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerEntityToResponseDto(savedSeller);

        return sellerResponseDto;

    }
}
