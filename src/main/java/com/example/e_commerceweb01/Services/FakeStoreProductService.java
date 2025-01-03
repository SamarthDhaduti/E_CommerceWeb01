package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import com.example.e_commerceweb01.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate ; //using this, you will be able to call 3rd party apis

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    /* call the external API to get single product
       https://fakestoreapi.com/products/1
     */
    @Override
    public Product getSinleProduct(long id) {
       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return FakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }
}
