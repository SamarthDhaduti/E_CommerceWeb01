package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/* we made it as an interface because lot of other class can implement this */

public interface ProductService {

    List<Product> getAllProducts();

    Product getSinleProduct(long id);

    Product createProduct(CreateProductRequestDto createProductRequestDto);



}
