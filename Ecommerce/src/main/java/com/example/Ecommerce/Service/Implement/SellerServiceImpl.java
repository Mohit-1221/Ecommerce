package com.example.Ecommerce.Service.Implement;

import com.example.Ecommerce.Dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.Enums.Category;
import com.example.Ecommerce.Exception.SellerNotFoundException;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Model.Seller;
import com.example.Ecommerce.Repository.ProductRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.Service.Interface.SellerService;
import com.example.Ecommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // request dto to entity
        Seller seller = SellerTransformer.requestDtoToSellerEntity(sellerRequestDto);

        // save to db
        Seller savedSeller = sellerRepository.save(seller);

        // entity to response dto
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerEntityToResponseDto(savedSeller);

        return sellerResponseDto;

    }

    @Override
    public SellerResponseDto updateSeller(String sellerEmailId, String name, String mobNo) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(sellerEmailId);
        if(seller != null){
            seller.setName(name);
            seller.setMobNo(mobNo);
        }
        else{
            throw new SellerNotFoundException("Seller doesn't Exist!");
        }
        Seller savedSeller = sellerRepository.save(seller);
        return SellerTransformer.sellerEntityToResponseDto(savedSeller);
    }

    @Override
    public List<String> getSellerByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        List<Seller> sellers = new ArrayList<>();
        Seller seller = new Seller();
        for(Product product : products){
            if(seller != product.getSeller()){
                sellers.add(product.getSeller());
            }
            seller = product.getSeller();
        }
        List<String> sellerList = new ArrayList<>();
        for(Seller sellerName: sellers ){
            sellerList.add(sellerName.getName());
        }
        return sellerList;
    }



}
