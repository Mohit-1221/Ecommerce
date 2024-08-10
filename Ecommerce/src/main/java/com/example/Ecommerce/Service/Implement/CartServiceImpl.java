package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.Ecommerce.Dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Exception.CustomerNotFoundException;
import com.example.Ecommerce.Exception.EmptyCartException;
import com.example.Ecommerce.Exception.InsufficientQuantityException;
import com.example.Ecommerce.Exception.InvalidCardException;
import com.example.Ecommerce.Model.*;
import com.example.Ecommerce.Repository.*;
import com.example.Ecommerce.Service.Interface.CartService;
import com.example.Ecommerce.Service.Interface.OrderService;
import com.example.Ecommerce.Transformer.CartTransformer;
import com.example.Ecommerce.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart); // save both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItems().add(savedItem);

        // prepare response dto
        return CartTransformer.CartToResponseDto(savedCart);

    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {

        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't Exist");
        }

        // check card
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!=checkOutCartRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card!!");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().isEmpty()){
            throw new EmptyCartException("Cart is Empty!!");
        }

        try {
            OrderEntity order = orderService.placeOrder(cart,card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientQuantityException e) {
            throw e;
        }
    }

    private void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }
}
