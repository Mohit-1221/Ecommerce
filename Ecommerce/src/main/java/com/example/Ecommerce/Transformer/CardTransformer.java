package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Model.Card;

public class CardTransformer {
    public static Card cardRequestdtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto cardToResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .build();
    }
}
