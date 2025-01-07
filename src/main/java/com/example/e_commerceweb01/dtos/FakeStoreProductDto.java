package com.example.e_commerceweb01.dtos;

import com.example.e_commerceweb01.Models.Category;
import com.example.e_commerceweb01.Models.Product;
import lombok.*;
;

/* This FakeStoreProductDto helps us to get the data from the third party */
/* You can use @Data also instead of Getter and Setter */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;


    /* here we are converting the output came by FakeStore into our Product Format */
    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);


        return product;
    }
}
