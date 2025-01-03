package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import com.example.e_commerceweb01.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    /* creating the instance of RestTemplate to work with 3rd Party API */

    private RestTemplate restTemplate ; //using this, you will be able to call 3rd party apis

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    /* call the external API to get single product
       https://fakestoreapi.com/products/1

       here we are creating object of FakeStoreProductDto because from here data will send to FakeStoreProductDto
       as an object and convert to Product and return to controller
     */
    @Override
    public Product getSinleProduct(long id) {
       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }
}
