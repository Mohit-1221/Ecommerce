package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.Enums.Gender;
import com.example.Ecommerce.Model.Cart;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Service.Interface.CustomerService;
import com.example.Ecommerce.Transformer.CartTransformer;
import com.example.Ecommerce.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(com.example.Ecommerce.Dto.RequestDto.CustomerRequestDto customerRequestDto) {
       // dto to entity
        Customer customer = CustomerTransformer.requestDtoToCustomer(customerRequestDto);
        Cart cart = CartTransformer.initialCart(customer);
        customer.setCart(cart);

        // save to db
        Customer savedCustomer = customerRepository.save(customer); // save both customer and cart

        // prepare response dto
        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToResponseDto(savedCustomer);
        return customerResponseDto;
    }

    @Override
    public List<String> getFemaleCustomersAged20To30(Gender gender, int startAge, int endAge) {
        List<Customer> customers = customerRepository.findByGenderAndAgeBetween(gender, startAge, endAge);
        List<String> customerList = new ArrayList<>();
        for(Customer customer : customers){
            customerList.add(customer.getName());
        }
        return customerList;
    }

    @Override
    public List<String> getMaleCustomerLessThan45(Gender gender,int age) {
        List<Customer> customers = customerRepository.findByGenderAndAgeLessThan(gender,age);
        List<String> customerList = new ArrayList<>();
        for(Customer customer : customers){
            customerList.add(customer.getName());
        }
        return customerList;
    }
}
