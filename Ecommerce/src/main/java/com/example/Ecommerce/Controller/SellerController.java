package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Exception.SellerNotFoundException;
import com.example.Ecommerce.Model.Seller;
import com.example.Ecommerce.Service.Interface.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public SellerResponseDto updateSeller(@RequestParam String sellerEmailId, @RequestParam String name, @RequestParam String mobNo) throws SellerNotFoundException {
        return sellerService.updateSeller(sellerEmailId,name,mobNo);
    }

    @GetMapping("/get_by_category")
    public List<String> getSellerByCategory(@RequestParam Category category){
        return sellerService.getSellerByCategory(category);
    }
}
