package com.example.Ecommerce.Service.Interface;

import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Enums.Gender;
import com.example.Ecommerce.Model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    List<String> getFemaleCustomersAged20To30(Gender gender, int startAge, int endAge);

    List<String> getMaleCustomerLessThan45(Gender gender,int age);
}
