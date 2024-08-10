package com.example.Ecommerce.Transformer;

import com.example.Ecommerce.Dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.Model.Customer;
import com.example.Ecommerce.Model.Item;
import com.example.Ecommerce.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity orderRequestDtoToOrder(Item item, Customer customer){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity order){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : order.getItems()){
            itemResponseDtoList.add(ItemTransformer.itemToResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderNo(order.getOrderNo())
                .orderDate(order.getOrderDate())
                .cardUsed(order.getCardUsed())
                .customerName(order.getCustomer().getName())
                .totalValue(order.getTotalValue())
                .items(itemResponseDtoList)
                .build();
    }
}
