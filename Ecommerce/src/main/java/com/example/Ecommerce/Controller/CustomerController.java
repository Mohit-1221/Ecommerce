package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Enums.Gender;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Service.Interface.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/female-aged-20-30")
    public List<String> getFemaleCustomersAged20To30(@RequestParam Gender gender,@RequestParam int startAge,@RequestParam int endAge) {
        return customerService.getFemaleCustomersAged20To30(gender,startAge,endAge);
    }

    @GetMapping("/male-less-than-45")
    public List<String> getMaleCustomerLessThan45(@RequestParam Gender gender,@RequestParam int age){
        return customerService.getMaleCustomerLessThan45(gender,age);
    }
}
