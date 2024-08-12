package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Model.Customer;

public class CustomerTransformer {

    public static Customer requestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto customerToResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }
}
