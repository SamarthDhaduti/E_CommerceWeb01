package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Exception.ProductNotFoundException;
import com.example.e_commerceweb01.Models.Category;
import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Repositories.CategoryRepository;
import com.example.e_commerceweb01.Repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    /* Now I am using my own Repository to fetch data so create an object of ProductRepositories */
    private ProductRepositories productRepositories;
    /* Now we need CategoryRepository also to get the category object so add that also */
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepositories productRepositories, CategoryRepository categoryRepository) {
        this.productRepositories = productRepositories;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepositories.findAll();
        return products;
    }

    @Override
    public Product getSinleProduct(long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepositories.findById(id);
        /* we have to check product with the given id doesn't exist
         either you can throw an exception further or you handle it
         we are making use of custom exception by using ProductNotFoundException class*/
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with the given id doesn't exist");
        }
       return optionalProduct.get();
    }

    @Override
    public Product createProduct(String title,
                                 double price,
                                 String description,
                                 String imageUrl,
                                 String category) {

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);

        /* Now here category is String but in the product model it's an object so get that object from
        CategoryRepository using the category string (which is the title in Category table) and check
        is the category with that title exists or not, if exists then add that object to product object
        else create a category with that title and then add that to product object
         */

        Category categoryFromDB = categoryRepository.findByTitle(category);

        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }
        else{
            product.setCategory(categoryFromDB);
        }

       /* To save the created product into DataBase */
        Product savedProduct;
        savedProduct = productRepositories.save(product);

        return savedProduct;

    }
}
