package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Exception.SellerNotFoundException;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.Seller;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.Interface.ProductService;
import com.example.Ecommerce.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller==null){
            throw new SellerNotFoundException("Seller doesn't Exist!");
        }

        // dto to entity
        Product product = ProductTransformer.requestDtoToProductEntity(productRequestDto);
        seller.getProducts().add(product);
        product.setSeller(seller);

        // save product to db
        Seller savedSeller = sellerRepository.save(seller); // save both product and seller
        //Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        // entity to response dto
        return ProductTransformer.productEntityToResponseDto(product);

    }


    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category,int price){
        List<Product> products = productRepository.findByCategoryAndPrice(category,price);

        // Prepare a list of dtos
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.productEntityToResponseDto(product));
        }
        return productResponseDtos;
    }
}
