package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.EmptyCartException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.InvalidCardException;
import com.example.Ecommerce.Model.Item;
import com.example.Ecommerce.Service.Interface.CartService;
import com.example.Ecommerce.Service.Interface.ItemService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item,itemRequestDto);
            return new ResponseEntity<>(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutCartRequestDto);
            return new ResponseEntity<>(orderResponseDto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
