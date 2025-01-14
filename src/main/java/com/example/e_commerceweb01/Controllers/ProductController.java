package com.example.e_commerceweb01.Controllers;

import com.example.e_commerceweb01.Exception.ProductNotFoundException;
import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Services.ProductService;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Product> getSinleProduct(@PathVariable("id") long id) throws ProductNotFoundException {

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
    /* Here actually when a user send a request to create a product returning that product object is not recommended
    because the info about that product is going to be stored inside the DB so there are chances of leaking
    confidential info, so create one more DTO ResponseDto and convert the returned product and send back the
    object of DTO with only needed info
     */
    @PostMapping("/products")
     public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        /* Here while calling the service class we have to pass the request body as an object coming from out side
        because service class doesn't directly interact with outside but controller will interact so take
        request body through CreateProductRequestDto.
         */
         return productService.createProduct(createProductRequestDto.getTitle(),
                 createProductRequestDto.getPrice(),
                 createProductRequestDto.getDescription(),
                 createProductRequestDto.getImage(),
                 createProductRequestDto.getCategory());
     }

     @GetMapping("/products/paginated")
         /* accept filter params which you're going to provide to the service, here we can't send thr request body
         int GET method so passing the Request Parameter
          */
        /* should i return page of prod or list of prod to the frontend??
         we have to send actually list of products because frontend doesn't know about the page
         please explore how to convert Page<T> to List<T> - HW */
     Page<Product> getPaginatedProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
         return productService.getPaginatedProducts(pageNo, pageSize);
     }
}
