package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Service.Interface.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
