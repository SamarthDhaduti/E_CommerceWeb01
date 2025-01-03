package com.example.e_commerceweb01.dtos;

import com.example.e_commerceweb01.Models.Category;
import com.example.e_commerceweb01.Models.Product;;

/* Using manual getters and setters */
/* This FakeStoreProductDto helps us to get the data from the third party */

public class FakeStoreProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /* here we are converting the output came by FakeStore into our Product Format */
    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);

        Category category1 = new Category();
        category1.setTitle(category);

        //product.setCategory(category1);
        product.setImageUrl(image);

        return product;
    }
}
