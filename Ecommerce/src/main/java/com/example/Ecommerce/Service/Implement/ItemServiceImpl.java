package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.OutOfStockException;
import com.example.Ecommerce.Exception.ProductNotFoundException;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Model.Item;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Service.Interface.ItemService;
import com.example.Ecommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, OutOfStockException, InsufficientQuantityException {
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("product doesn't Exist!");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't Exist");
        }

        Product product = optionalProduct.get();

        if(product.getQuantity()==0){
            throw new OutOfStockException("Product is out of stock!");
        }

        if(product.getQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
