package com.example.e_commerceweb01.Controllers;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Services.ProductService;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    /* need an instance of service class inside the controller class so that it can call the service class
    when a request comes
     */
    public ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
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
    public ResponseEntity<Product> getSinleProduct(@PathVariable("id") long id){

        /* Now we have to return the ResponseEntity so get the product from the service */
        Product p = productService.getSinleProduct(id);
        /* Create object of ResponseEntity and pass the product object to ResponseEntity and return it */
        ResponseEntity<Product> responseEntity;

        if(p == null){
            responseEntity = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        return responseEntity;
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
        /* Here while calling the service class we have to pass the request body coming from out side
        because service class doesn't directly interact with outside but controller will interact so take
        request body through CreateProductRequestDto.
         */
         return productService.createProduct(createProductRequestDto.getTitle(),
                 createProductRequestDto.getPrice(),
                 createProductRequestDto.getDescription(),
                 createProductRequestDto.getImage(),
                 createProductRequestDto.getCategory());
     }
}
