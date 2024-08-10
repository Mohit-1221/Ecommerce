package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Enums.ProductStatus;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.Seller;

public class ProductTransformer {
    public static Product requestDtoToProductEntity(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productEntityToResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .category(product.getCategory())
                .price(product.getPrice())
                .sellerName(product.getSeller().getName())
                .build();
    }
}
