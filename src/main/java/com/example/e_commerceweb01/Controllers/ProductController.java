package com.example.e_commerceweb01.Controllers;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Services.ProductService;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    /*
    at the end of the day
    api = method in my controller
     */

    /*
    GET /products
     */

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    /*
    GET /products/{id}
     */
    @GetMapping("/products/{id}")
    public Product getSinleProduct(@PathVariable("id") long id){
        return productService.getSinleProduct(id);
    }

    /*
    Create a product
    {
        title :
        description:
        price:
        category:
    } => payload / request body
    POST /products
     */
    @PostMapping("/products")
     public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
         return productService.createProduct(createProductRequestDto);
     }
}
