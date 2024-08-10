package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Enums.ProductStatus;
import com.example.Ecommerce.Exception.*;
import com.example.Ecommerce.Model.*;
import com.example.Ecommerce.Repository.CardRepository;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.OrderRepository;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Service.Interface.OrderService;
import com.example.Ecommerce.Transformer.ItemTransformer;
import com.example.Ecommerce.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException {
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't Exist");
        }

        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't Exist!");
        }

        Product product = productOptional.get();

        // check quantity
        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        // check card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card!!");
        }

        // check quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        // create item
        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
       // item.setCart(customer.getCart());

        // create order
        OrderEntity order = OrderTransformer.orderRequestDtoToOrder(item,customer);

        String maskedCardNo = generateMaskedCardNo(card);
        order.setCardUsed(maskedCardNo);

        order.getItems().add(item);
        item.setOrder(order);

        OrderEntity savedOrder = orderRepository.save(order); // save both order and item
        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        // prepare response dto
        return OrderTransformer.orderToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));

        int totalValue = 0;
        for(Item item : cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity()>product.getQuantity()){
                throw new InsufficientQuantityException("Required quantity not present");
            }

            totalValue += item.getRequiredQuantity()* product.getPrice();
            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if(newQuantity == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            item.setOrder(orderEntity);
        }
        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    private String generateMaskedCardNo(Card card) {
        StringBuilder cardNo = new StringBuilder();
        String originalCardNo = card.getCardNo();
        for(int i=0; i<originalCardNo.length()-4; i++){
            cardNo.append("X");
        }
        cardNo.append(originalCardNo.substring(originalCardNo.length() - 4));
        return cardNo.toString();
    }
}