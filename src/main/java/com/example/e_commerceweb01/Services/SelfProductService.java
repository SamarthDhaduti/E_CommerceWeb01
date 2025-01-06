package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    /* Now I am using my own Repository to fetch data so create an object of ProductRepositories */
    private ProductRepositories productRepositories;

    public SelfProductService(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepositories.findAll();
        return products;
    }

    @Override
    public Product getSinleProduct(long id) {
        return null;
    }

    @Override
    public Product createProduct(String title,
                                 double price,
                                 String description,
                                 String imageUrl,
                                 String category) {
        return null;
    }
}
