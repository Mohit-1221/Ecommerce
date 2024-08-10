package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Model.Card;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Service.Interface.CardService;
import com.example.Ecommerce.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid Email Id!!");
        }

        // convert dto to entity
        Card card = CardTransformer.cardRequestdtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        // save to db
        Customer savedCustomer = customerRepository.save(customer); // save both customer and card

        // prepare response dto
        return CardTransformer.cardToResponseDto(card);

    }
}
